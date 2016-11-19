package com.zhihudaily.model;

import java.util.List;

/**
 * Created by liuhao on 2016/11/5.
 */

public class JieCaoResponse {
    private int showapi_res_code;
    private String showapi_res_error;
    private ResponseBody showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ResponseBody getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ResponseBody showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public class ResponseBody{
        private int ret_code;
        private PageBean pagebean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PageBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PageBean pagebean) {
            this.pagebean = pagebean;
        }

        public class PageBean{
            private int allPages;
            private List<JieCaoCommonEntity> contentlist;

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public void setContentlist(List<JieCaoCommonEntity> contentlist) {
                this.contentlist = contentlist;
            }

            public List<JieCaoCommonEntity> getContentlist() {
                return contentlist;
            }

            public int getAllPages() {
                return allPages;
            }
        }
    }
}
