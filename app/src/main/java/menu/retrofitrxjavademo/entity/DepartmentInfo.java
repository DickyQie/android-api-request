package menu.retrofitrxjavademo.entity;

import java.util.List;

/**
 * Created by zq on 2016/8/5.
 */

public class DepartmentInfo extends User {

    private List<Data> data;
    public class Data
    {
        private String departid;

        private String departname;

        private String head;

        private String address;

        public void setDepartid(String departid){

            this.departid = departid;
        }
        public String getDepartid(){
            return this.departid;
        }
        public void setDepartname(String departname){

            this.departname = departname;
        }
        public String getDepartname(){
            return this.departname;
        }
        public void setHead(String head){

            this.head = head;
        }
        public String getHead()
        {
            return this.head;
        }
        public void setAddress(String address){

            this.address = address;
        }
        public String getAddress(){

            return this.address;
        }
        @Override
        public String toString() {
            return departid+"--"+departname+"--"+head+"--"+address;
        }
    }
        public void setData(List<Data> data){

            this.data = data;
        }
        public List<Data> getData(){
            return this.data;
        }


}

