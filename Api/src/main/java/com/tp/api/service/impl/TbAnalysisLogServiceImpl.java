package com.tp.api.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.entity.TbAnalysisLog;
import com.tp.api.entity.TbString;
import com.tp.api.mapper.TbAnalysisLogMapper;
import com.tp.api.mode.AnalysLogRequest;
import com.tp.api.mode.EchartBean;
import com.tp.api.mode.EchartBean.XAxisBean;
import com.tp.api.mode.StringFilterRequestParam;
import com.tp.api.service.TbAnalysisLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.utils.DateUtis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanping
 * @since 2018-10-19
 */
@Service
public class TbAnalysisLogServiceImpl extends ServiceImpl<TbAnalysisLogMapper, TbAnalysisLog> implements TbAnalysisLogService {

    @Override
    public List<TbAnalysisLog> getGroupBy(String name ) {
        Condition condition =  Condition.create();
        condition.where("count != {0}",0);
        condition.groupBy(name);

        List<TbAnalysisLog> groups = baseMapper.selectList(condition);
        return groups;
    }

    @Override
    public Page<TbAnalysisLog> getLastMonth(TbAnalysisLog request) {
        EntityWrapper condition =  new EntityWrapper();
        if (!StringUtils.isEmpty(request.getDomain())) {
            condition.where("domain = {0}", request.getDomain());
        }
        condition.where("count !={0}",0);
        condition.orderBy("month_day");
        Page page = new Page<TbString>(0, 30);
        EntityWrapper<StringFilterRequestParam> entityWrapper = new EntityWrapper<>();
        List<TbString> tbStrings = baseMapper.selectPage(page,condition);
        page.setRecords(tbStrings);
        return page;
    }

    @Override
    public EchartBean createEchartBean(AnalysLogRequest analysLogRequest) {

        List<TbAnalysisLog> tbAnalysisLogs = getGroupBy("domain");

        EchartBean bean = new EchartBean();

        //线分类
        bean.legend = createLlegend(tbAnalysisLogs);
        bean.createToolBox();

        List<EchartBean.SeriesBean> seriesBeans = new ArrayList<>();
        bean.series = seriesBeans;

        //拿到所有的月份
        List<TbAnalysisLog> monthDays = getGroupBy("month_day");
        monthDays.sort(new Comparator<TbAnalysisLog>() {
            @Override
            public int compare(TbAnalysisLog o1, TbAnalysisLog o2) {
                return o1.getUpdateDate().compareTo(o2.getUpdateDate());
            }
        });


        bean.xAxis = createXAxis(monthDays);

        HashMap<String ,List<TbAnalysisLog>> maps = new HashMap<>();

        for (TbAnalysisLog analysisLog : tbAnalysisLogs){
            Page<TbAnalysisLog>  monthDay = getLastMonth(analysisLog);
            maps.put(analysisLog.getDomain(),monthDay.getRecords());

        }

        Iterator<String> it = maps.keySet().iterator();
        while (it.hasNext()){
            String domain = it.next();
            List<TbAnalysisLog> tbAnalysisLogs1 =  maps.get(domain);
            EchartBean.SeriesBean seriesBean = createLSeries(domain,tbAnalysisLogs1,bean.xAxis);
            seriesBeans.add(seriesBean);
        }

        return bean;
    }


    @Override
    public TbAnalysisLog saveAndUpdate(TbAnalysisLog request) {

        EntityWrapper condition =  new EntityWrapper();
        if (!StringUtils.isEmpty(request.getDomain())) {
            condition.where("domain = {0}", request.getDomain());
        }else {
            return request;
        }

        if (!StringUtils.isEmpty(request.getMonthDay())) {
            condition.where("month_day = {0}", request.getMonthDay());
        }else {
            return request;
        }

        List<TbAnalysisLog> tbAnalysisLogs =  selectList(condition);
        if (tbAnalysisLogs !=null && tbAnalysisLogs.size()>0){
            TbAnalysisLog result = tbAnalysisLogs.get(0);

            request.setId(result.getId());
            request.setCount(result.getCount() +1);
        }

        request.setHour(DateUtis.getCurrentHour()+"");
        insertOrUpdate(request);
        return request;
    }

    @Override
    public TbAnalysisLog getMaxChart() {

        EntityWrapper condition =  new EntityWrapper();

        String mmDD =  DateUtis.formatMMDD();
        condition.where("month_day = {0}", mmDD);

        condition.orderBy("count",false);
        List<TbAnalysisLog> tbAnalysisLogs =  selectList(condition);
        if (tbAnalysisLogs !=null && tbAnalysisLogs.size()>0) {
                return tbAnalysisLogs.get(0);
        }

            return null;
    }

    @Override
    public TbAnalysisLog getCurDaySumChart() {

        EntityWrapper condition =  new EntityWrapper();

        String mmDD =  DateUtis.formatMMDD();
        condition.where("month_day = {0}", mmDD);

        condition.orderBy("count",false);
        List<TbAnalysisLog> tbAnalysisLogs =  selectList(condition);
        TbAnalysisLog result = null;
        for (TbAnalysisLog tbAnalysisLog: tbAnalysisLogs){
            if (result == null){
                result = tbAnalysisLog;
            }else {
                result.setCount(result.getCount() + tbAnalysisLog.getCount());
            }
        }


        return result;
    }


    /**
     * 创建 数据
     *
     * @param domain
     * @param analysisLogs
     * @param xAxis
     * @return
     */
    private EchartBean.SeriesBean createLSeries(String domain, List<TbAnalysisLog> analysisLogs, XAxisBean xAxis){

        EchartBean.SeriesBean seriesBean = new EchartBean.SeriesBean();
        List<Integer> data = new LinkedList<>();

        List<String> monthDays =  xAxis.data;

        //拿到月份
        for (String monthDay : monthDays){
            int count = 0;
            for (TbAnalysisLog analysisLog : analysisLogs){
                if (analysisLog.getMonthDay().equals(monthDay)) {
                    count = analysisLog.getCount();
                    break;
                }
            }
            data.add(count);
        }



        seriesBean.data =data;
        seriesBean.type = "line";
        seriesBean.smooth = true;
        seriesBean.name = analysisLogs.get(0).getDomain();

        return seriesBean;
    }

    private EchartBean.LegendBean createLlegend(List<TbAnalysisLog>  analysisLogs){
        EchartBean.LegendBean legendBean = new EchartBean.LegendBean();
        List<String> data = new LinkedList<>();

//        legendBean.top = "10%";
        legendBean.data = data;
        for (TbAnalysisLog analysisLog : analysisLogs){
            data.add(analysisLog.getDomain());
        }

        return legendBean;
    }

    private XAxisBean createXAxis(List<TbAnalysisLog>  analysisLogs){
        XAxisBean exAxisBean = new XAxisBean();
        exAxisBean.type = "category";
        List<String> data = new LinkedList<>();
        exAxisBean.data = data;
        for (TbAnalysisLog analysisLog : analysisLogs){
            data.add(analysisLog.getMonthDay());
        }


        return exAxisBean;
    }



}
