package com.abc.code.bean;

/**
 * author: hedianbo.
 * date: 2020/5/9
 * desc:
 */
public class KongQi {


    /**
     * errno : 0
     * data : {"update_at":"2020-04-29 17:00:42","unit":"","id":"kongshi","unit_symbol":"","current_value":23}
     * error : succ
     */

    private int errno;
    private DataBean data;
    private String error;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * update_at : 2020-04-29 17:00:42
         * unit :
         * id : kongshi
         * unit_symbol :
         * current_value : 23
         */

        private String update_at;
        private String unit;
        private String id;
        private String unit_symbol;
        private int current_value;

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUnit_symbol() {
            return unit_symbol;
        }

        public void setUnit_symbol(String unit_symbol) {
            this.unit_symbol = unit_symbol;
        }

        public int getCurrent_value() {
            return current_value;
        }

        public void setCurrent_value(int current_value) {
            this.current_value = current_value;
        }
    }
}
