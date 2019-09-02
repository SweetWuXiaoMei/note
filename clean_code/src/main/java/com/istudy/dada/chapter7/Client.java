package com.istudy.dada.chapter7;

public class Client {

    // region 错误示例
    /*public static void main(String[] args) {

        Api api = new Api();
        try {
            api.request();
        } catch (InvalidParamException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (NetworkException e) {
            e.printStackTrace();
        }
    }*/

    // endregion

    // region 正确示例
    public static void main(String[] args) {

         ApiContext apiContext = new ApiContext();
        try {
            apiContext.request();
        } catch (RequestFailureException e) {
            e.printStackTrace();
        }
    }
    // endregion
}
