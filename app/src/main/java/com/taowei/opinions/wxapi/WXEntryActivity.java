package com.taowei.opinions.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.taowei.opinions.data.WXBaseRespEntity;
import com.taowei.opinions.utils.LogUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import static com.taowei.opinions.app.Config.APP_ID_WX;



/**
 * Created by Administrator on 2017/8/19.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

//    private static final String APP_ID = "wx435df93d956b1196";
//    private static final String APP_SECRET = "a17d32c8c2b97c0fde630e23344f8be2";


    /**
     * 微信登录相关
     */
    private IWXAPI api;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, APP_ID_WX, true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID_WX);

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {

                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Gson gson = new Gson();
        WXBaseRespEntity entity = gson.fromJson(gson.toJson(baseResp), WXBaseRespEntity.class);
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";

                String code = entity.getCode();
                LogUtil.e("CODE", code);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";

                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";

                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";

                break;
            default:
                result = "发送返回";
//                showMsg(0,result);
                finish();
                break;
        }
        Toast.makeText(WXEntryActivity.this, result, Toast.LENGTH_LONG).show();

    }


}
