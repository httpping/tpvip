package com.tp.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tp.api.config.websocket2.MyWebSocket;
import com.tp.api.constant.ReturnCodeEnum;
import com.tp.api.mode.BaseResponse;
import com.tp.api.utils.ServerUtils;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Locale;


@Slf4j
@Controller
public class DemoControl {



    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/hello")
    public BaseResponse sayHello() throws NotFoundException {
        log.info("hello world!");
        BaseResponse baseResult = new BaseResponse();
        baseResult.data = "hello word";
        String msg1 = this.messageSource.getMessage("message.order.stockout", null, Locale.CHINA);

        String demo = "{\"statusCode\":200,\"result\":{\"goods_list\":[{\"cart_list\":[{\"session_id\":\"##h9HtMWyeY2Q99J3bF9x6c7r\\/TG1YmslGi9IYnwunBCg=\",\"shelf_down_type\":0,\"channel_type\":null,\"is_group_recommend\":\"0\",\"goods_title\":\"Floral Lace Up Bikini Set\",\"goods_name\":\"Flower Bikini Set\",\"is_lang_show_cur\":\"1\",\"goods_grid_app\":\"zaful\\/pdm-product-pic\\/Clothing\\/2018\\/07\\/27\\/goods-grid-app\\/1535927521094300393.jpg\",\"rec_id\":\"8400\",\"shipping_method\":\"1,2,4,5\",\"user_id\":\"5773\",\"goods_id\":\"565396\",\"goods_sn\":\"275408901\",\"buy_number\":1,\"goods_number\":76,\"market_price\":23.16,\"goods_attr_id\":\"12430546,12430547\",\"goods_img\":\"https:\\/\\/gloimg.zaful.com\\/zaful\\/pdm-product-pic\\/Clothing\\/2018\\/07\\/27\\/goods-img\\/1535927521212655608.jpg\",\"is_free_shipping\":\"1\",\"goods_weight\":\"0.215\",\"goods_volume_weight\":\"0.215\",\"cat_id\":\"67\",\"url_title\":\"floral-lace-up-bikini-set\",\"subtotal\":17.99,\"goods_off\":\"0\",\"custom_size\":\"\",\"addtime\":\"1537871146\",\"last_modified\":3600,\"is_dinghuo_goods\":\"0\",\"is_promote\":0,\"promote_price\":\"\",\"promote_start_date\":\"0\",\"promote_end_date\":\"0\",\"is_selected\":1,\"if_collect\":0,\"group_goods_id\":\"565396\",\"mobile_price\":null,\"mobile_sale_start_time\":null,\"mobile_sale_end_time\":null,\"mobile_status\":null,\"mobile_is_set_time\":null,\"discountPriceType\":\"0\",\"shop_price\":\"17.99\",\"goods_state\":0,\"cat_name\":\"Bikinis\",\"tags\":[],\"is_mobile_price\":0,\"is_cod\":0,\"attr_size\":\"S\",\"attr_color\":\"MAUVE\",\"multi_attr\":[],\"cat_level_column\":{\"first_cat_name\":\"Swimwear\",\"snd_cat_name\":\"Bikinis\"},\"wp_image\":\"https:\\/\\/gloimg.zaful.com\\/zaful\\/pdm-product-pic\\/Clothing\\/2018\\/07\\/27\\/goods-grid-app\\/1535927521094300393.jpg\"},{\"session_id\":\"##h9HtMWyeY2Q99J3bF9x6c7r\\/TG1YmslGi9IYnwunBCg=\",\"shelf_down_type\":0,\"channel_type\":null,\"is_group_recommend\":\"0\",\"goods_title\":\"Floral Lace Up Bikini Set\",\"goods_name\":\"Flower Bikini Set\",\"is_lang_show_cur\":\"1\",\"goods_grid_app\":\"zaful\\/pdm-product-pic\\/Clothing\\/2018\\/07\\/27\\/goods-grid-app\\/1535927526465522409.jpg\",\"rec_id\":\"8399\",\"shipping_method\":\"1,2,4,5\",\"user_id\":\"5773\",\"goods_id\":\"565397\",\"goods_sn\":\"275408902\",\"buy_number\":1,\"goods_number\":79,\"market_price\":23.16,\"goods_attr_id\":\"12430554,12430555\",\"goods_img\":\"https:\\/\\/gloimg.zaful.com\\/zaful\\/pdm-product-pic\\/Clothing\\/2018\\/07\\/27\\/goods-img\\/1535927526103515486.jpg\",\"is_free_shipping\":\"1\",\"goods_weight\":\"0.215\",\"goods_volume_weight\":\"0.215\",\"cat_id\":\"67\",\"url_title\":\"floral-lace-up-bikini-set\",\"subtotal\":17.99,\"goods_off\":\"0\",\"custom_size\":\"\",\"addtime\":\"1537871133\",\"last_modified\":3600,\"is_dinghuo_goods\":\"0\",\"is_promote\":0,\"promote_price\":\"\",\"promote_start_date\":\"0\",\"promote_end_date\":\"0\",\"is_selected\":1,\"if_collect\":0,\"group_goods_id\":\"565396\",\"mobile_price\":null,\"mobile_sale_start_time\":null,\"mobile_sale_end_time\":null,\"mobile_status\":null,\"mobile_is_set_time\":null,\"discountPriceType\":\"0\",\"shop_price\":\"17.99\",\"goods_state\":0,\"cat_name\":\"Bikinis\",\"tags\":[],\"is_mobile_price\":0,\"is_cod\":0,\"attr_size\":\"M\",\"attr_color\":\"MAUVE\",\"multi_attr\":[],\"cat_level_column\":{\"first_cat_name\":\"Swimwear\",\"snd_cat_name\":\"Bikinis\"},\"wp_image\":\"https:\\/\\/gloimg.zaful.com\\/zaful\\/pdm-product-pic\\/Clothing\\/2018\\/07\\/27\\/goods-grid-app\\/1535927526465522409.jpg\"}],\"goods_module_type\":0,\"reduc_id\":0,\"activity_type\":0,\"goods_price_lowhight\":[\"17.99\",\"17.99\"],\"msg\":\"My items\",\"url\":\"\"},{\"cart_list\":[{\"session_id\":\"##h9HtMWyeY2Q99J3bF9x6c7r\\/TG1YmslGi9IYnwunBCg=\",\"shelf_down_type\":0,\"channel_type\":\"1\",\"is_group_recommend\":\"0\",\"goods_title\":\"Charm Star Collarbone Chain Necklace\",\"goods_name\":\"Star Charm Collarbone Chain Necklace\",\"is_lang_show_cur\":\"1\",\"goods_grid_app\":\"zaful\\/pdm-product-pic\\/Clothing\\/2017\\/08\\/15\\/goods-grid-app\\/1502771051481900470.jpg\",\"rec_id\":\"8398\",\"shipping_method\":\"1,2,4,5\",\"user_id\":\"5773\",\"goods_id\":\"307418\",\"goods_sn\":\"223359601\",\"buy_number\":1,\"goods_number\":429,\"market_price\":4.66,\"goods_attr_id\":\"11964009\",\"goods_img\":\"https:\\/\\/gloimg.zaful.com\\/zaful\\/pdm-product-pic\\/Clothing\\/2017\\/08\\/15\\/goods-img\\/1502771051372001394.jpg\",\"is_free_shipping\":\"1\",\"goods_weight\":\"0.030\",\"goods_volume_weight\":\"0.030\",\"cat_id\":\"29\",\"url_title\":\"charm-star-collarbone-chain-necklace\",\"subtotal\":3.75,\"goods_off\":\"0\",\"custom_size\":\"\",\"addtime\":\"1537871120\",\"last_modified\":3600,\"is_dinghuo_goods\":\"0\",\"is_promote\":0,\"promote_price\":\"\",\"promote_start_date\":\"1526983740\",\"promote_end_date\":\"1529662140\",\"is_selected\":1,\"if_collect\":0,\"group_goods_id\":\"307418\",\"mobile_price\":\"2.43\",\"mobile_sale_start_time\":\"1505318400\",\"mobile_sale_end_time\":\"1506182399\",\"mobile_status\":\"1\",\"mobile_is_set_time\":\"1\",\"discountPriceType\":\"0\",\"shop_price\":\"3.75\",\"goods_state\":0,\"cat_name\":\"Necklaces\",\"tags\":[],\"is_mobile_price\":0,\"is_cod\":0,\"attr_size\":\"\",\"attr_color\":\"GOLDEN\",\"multi_attr\":[],\"cat_level_column\":{\"first_cat_name\":\"Accessories\",\"snd_cat_name\":\"Jewelry\",\"third_cat_name\":\"Necklaces\"},\"wp_image\":\"https:\\/\\/gloimg.zaful.com\\/zaful\\/pdm-product-pic\\/Clothing\\/2017\\/08\\/15\\/goods-grid-app\\/1502771051481900470.jpg\"}],\"goods_module_type\":1,\"reduc_id\":0,\"activity_type\":0,\"goods_price_lowhight\":[\"3.75\"],\"msg\":\"ADD 1<\\/b><\\/font> TO GET 30%<\\/b><\\/font> OFF\",\"url\":\"zaful:\\/\\/action?actiontype=14&url=bestsellers&name=Buy 1 Get 20% Off\",\"save_type_child\":3}],\"goods_share_list\":{\"307418\":{\"amount\":0.75,\"save_type\":3}},\"channel_save_total\":0.75,\"full_save_total\":0,\"is_show_fast_payment\":1,\"cart_subtotal\":39.73,\"cart_total_number\":3,\"cart_total_amount\":\"38.98\",\"cart_discount_amount\":\"0.75\",\"cartRadioHint\":\"\",\"cart_shipping_free_amount\":0,\"cart_shipping_free_amount_replace\":\"$0\",\"cartUserHint\":\"In the next step use the discount coupon. \"},\"msg\":\"\"} \n";

        String ip= ServerUtils.getServerHost();
        log.info(ip);

        try {
            MyWebSocket.sendInfo(demo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseResult.message = ReturnCodeEnum.CODE_200.getValue();
//        throw  new NotFoundException(msg1);
        return  baseResult;
    }
}
