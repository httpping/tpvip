package com.tp.api.mode;

import javafx.scene.control.Tooltip;

import java.util.ArrayList;
import java.util.List;

/**
 * 报表
 *
 * http://echarts.baidu.com/echarts2/doc/example/line1.html
 */
public class EchartBean {
    public TitleBean title;
    public TooltipBean tooltip;
    public ToolboxBean toolbox;
    public LegendBean legend;
    public XAxisBean xAxis;
    public YAxisBean yAxis;
    public List<SeriesBean> series;

    public static class TitleBean {
        public String left;
        public String text;
    }

    public static class TooltipBean {
        public String trigger;
        public AxisPointerBean axisPointer;

        public static class AxisPointerBean {
            public String type;
        }
    }

    public static class ToolboxBean {
        public boolean show;
        public FeatureBean feature;
        public String right ;

        public static class FeatureBean {
            public DataViewBean dataView;
            public RestoreBean restore;
            public SaveAsImageBean saveAsImage;
            public MagicTypeBean magicType;

            public static class DataViewBean {
                public boolean show;
            }

            public static class RestoreBean {
                public boolean show;
            }

            public static class DataZoomBean {
                public boolean show;
            }

            public static class SaveAsImageBean {
                public boolean show;
            }

            public static class MagicTypeBean {
                public List<String> type;
            }
        }
    }

    public static class LegendBean {
        public String top;
        public List<String> data;
    }

    public static class XAxisBean {
        public String type;
        public boolean boundaryGap;
        public List<String> data;
    }

    public static class YAxisBean {
        public String top;
        public String type;
        public AxisLabelBean axisLabel;
        public AxisPointerBeanX axisPointer;

        public static class AxisLabelBean {
            public String formatter;
        }

        public static class AxisPointerBeanX {
            public boolean snap;
        }
    }

    public static class SeriesBean {
        public String name;
        public String type;
        public String color;
        public boolean smooth;
        public List<Integer> data;
    }


    public void createToolBox(){
        ToolboxBean toolboxBean = new ToolboxBean();
        toolboxBean.show =true;
        toolboxBean.right = "10%";
        toolboxBean.feature = new ToolboxBean.FeatureBean();

        toolboxBean.feature.dataView = new ToolboxBean.FeatureBean.DataViewBean();
        toolboxBean.feature.dataView.show =true;

        toolboxBean.feature.saveAsImage = new ToolboxBean.FeatureBean.SaveAsImageBean();
        toolboxBean.feature.saveAsImage.show =true;

        toolboxBean.feature.magicType  = new ToolboxBean.FeatureBean.MagicTypeBean();
        toolboxBean.feature.magicType.type = new ArrayList<String>();
        toolboxBean.feature.magicType.type.add("bar");
        toolboxBean.feature.magicType.type.add("line");
        toolboxBean.feature.magicType.type.add("stack");
        toolboxBean.feature.magicType.type.add("tiled");
        toolboxBean.feature.magicType.type.add("tiled");
        toolboxBean.feature.magicType.type.add("chord");
        toolboxBean.feature.magicType.type.add("pie");
        toolboxBean.feature.magicType.type.add("pie");

        toolboxBean.feature.restore = new ToolboxBean.FeatureBean.RestoreBean();
        toolboxBean.feature.restore.show =true;

        this.toolbox = toolboxBean;
        TooltipBean tooltip = new TooltipBean();
        tooltip.trigger = "axis";
        tooltip.axisPointer = new TooltipBean.AxisPointerBean();
        tooltip.axisPointer.type = "cross";
        this.tooltip = tooltip;


        TitleBean titleBean = new TitleBean();
        titleBean.left ="10%";
        titleBean.text = "接口调用频率分布";
        this.title = titleBean;
        this.yAxis = createYAxis();
    }


    private YAxisBean createYAxis(){
        YAxisBean yAxisBean = new YAxisBean();
        yAxisBean.type = "value";

        YAxisBean.AxisLabelBean axisLabelBean = new YAxisBean.AxisLabelBean();
        axisLabelBean.formatter = "{value} 次";

        yAxisBean.axisLabel = axisLabelBean;

        YAxisBean.AxisPointerBeanX axisPointerBeanX = new YAxisBean.AxisPointerBeanX();
        axisPointerBeanX.snap =true;

        yAxisBean.axisPointer = axisPointerBeanX;

        return yAxisBean;
    }
}
