package com.spy.easyframe.model;

import android.util.Log;

import com.spy.easyframe.model.impl.IBaseModel;
import com.spy.easyframe.network.NetWork;
import com.spy.easyframe.presenter.impl.ILiveListPresernter;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/24.
 */

public class LiveListModel implements IBaseModel{

    /**
     * code : 0
     * msg : 分析师团队
     * startIndex : 0
     * total : 0
     * pageSize : 30
     * curPage : 0
     * items : []
     * item : {"teamList":[{"liveTeam":{"fitInterest":2,"signature":"轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写","level":2,"threshold":"5000.00元","currentInterest":"1200047803.69元","everAuth":2,"names":"  转身离开  转身离开  Merlin  asda12312312312","sortid":2,"currmemberid":5,"headimgurl":"http://cdn.qilin99.cn/assets/img/banner/banner1.jpg","name":"赢在中国2","liveTime":"15:00-16:55  18:15-19:55","id":3,"livetime3":null,"livetime2":"18:15-19:55","temporary2":null,"needInterest":"8000.00元","livetime1":"15:00-16:55","status":1,"newImageUrl":"http://cdn.qilin99.cn/ctrade_cms/img/live_1481871631372"},"teamMemberList":[{"id":4,"username":"songjiang","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂","teamid":3,"nickname":"转身离开","token":"9761dba834829cf24f91f2de7f53404b","headimgurl":"http://cdn.qilin99.cn/test/2.jpg","jobtitle":"金牌分析师"},{"id":5,"username":"wuyong","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂","teamid":3,"nickname":"转身离开","token":"9761dba834829cf24f91f2de7f53404b","headimgurl":"http://7xqjs9.com2.z0.glb.qiniucdn.com/img/avatar/2_1453968172348","jobtitle":"金牌分析师"},{"id":8,"username":"raoxiang","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂","teamid":3,"nickname":"Merlin","token":"2c67e758c73aeac13c56a58bb26e2629","headimgurl":"http://wp.500win.cn/ctrade/img/user.jpg","jobtitle":"金牌分析师"},{"id":9,"username":"asd","passwd":"","brief":"qwdqw                 ","goodat":"            asdasd                ","teamid":3,"nickname":"asda12312312312","token":"","headimgurl":"http://wp.500win.cn/ctrade/img/user.jpg","jobtitle":"dasd"}]},{"liveTeam":{"fitInterest":2,"signature":"哈哈123","level":2,"threshold":"3000.00元","currentInterest":"1200047803.69元","everAuth":2,"names":"  Merlin  转身离开  cewc","sortid":3,"currmemberid":2,"headimgurl":"http://cdn.qilin99.cn/assets/img/banner/banner1.jpg","name":"赢在中国1","liveTime":"15:00-16:55","id":1,"livetime3":null,"livetime2":null,"temporary2":null,"needInterest":"8000.00元","livetime1":"15:00-16:55","status":0,"newImageUrl":"http://cdn.qilin99.cn/ctrade_cms/img/live_1481871600171"},"teamMemberList":[{"id":1,"username":"Merlin","passwd":"","brief":"我喊你一声,你敢答应吗?","goodat":"打杂","teamid":1,"nickname":"Merlin","token":"2c67e758c73aeac13c56a58bb26e2629","headimgurl":"http://cdn.qilin99.cn/live_cms/img/default/1_1482403675876","jobtitle":"金牌分析师"},{"id":2,"username":"gaohaiqing","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂,shiba 3333","teamid":1,"nickname":"转身离开","token":"34bea06e23c7035c4aca664d47bb3cc1","headimgurl":"http://cdn.qilin99.cn/live_cms/img/default/2_1465895803684","jobtitle":"金牌分析师"},{"id":11,"username":"juy","passwd":"","brief":"           zxc                 ","goodat":"         zc                   ","teamid":1,"nickname":"cewc","token":"","headimgurl":"","jobtitle":"cczx"}]}],"banner":[{"id":105,"date":"2016-12-21 16:42:25","img_url":"http://cdn.qilin99.cn/ctrade_cms/img/banner_1482077157619","action":"url","url":"http://wp.500win.cn/ctrade/activity/201612/lastpomp/lastpomp.html?redirect=1","title":"油市平衡恐黄粱一梦","type":"home,live","sortId":49,"display":1,"time":-1,"iconurl":"","sharebrief":"","isandroid":true,"ish5":true,"isios":true,"ispc":true,"isweixin":true,"activityname":"","startdate":"","enddate":"","starttime":"","endtime":""},{"id":70,"date":"2016-11-28 16:28:00","img_url":"http://cdn.qilin99.cn/ctrade_cms/img/banner_1482218145126","action":"url","url":"http://wp.500win.cn/ctrade/activity/201612/lastpomp/lastpomp.html?redirect=1","title":"都是加息惹的祸 银价跌跌不休","type":"home,faxian,live","sortId":6,"display":1,"time":-1,"iconurl":"","sharebrief":"","isandroid":true,"ish5":true,"isios":true,"ispc":true,"isweixin":true,"activityname":"","startdate":"","enddate":"","starttime":"","endtime":""},{"id":115,"date":"2016-12-16 17:21:25","img_url":"http://cdn.qilin99.cn/ctrade_cms/img/default_1481373265979","action":"url","url":"http://wp.500win.cn/ctrade/activity/201612/lastpomp/pompnow.html?redirect=1","title":"年终盛典，带你畅游全球","type":"home,faxian,live","sortId":0,"display":1,"time":2,"iconurl":"http://cdn.qilin99.cn/ctrade_cms/img/icon_1481179649144","sharebrief":"啥事","isandroid":true,"ish5":true,"isios":true,"ispc":true,"isweixin":true,"activityname":"","startdate":"","enddate":"","starttime":"","endtime":""}]}
     * totalPage : 0
     */

    private int code;
    private String msg;
    private int startIndex;
    private int total;
    private int pageSize;
    private int curPage;
    private ItemBean item;
    private int totalPage;
    private List<?> items;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public static class ItemBean {
        private List<TeamListBean> teamList;
        private List<BannerBean> banner;

        public List<TeamListBean> getTeamList() {
            return teamList;
        }

        public void setTeamList(List<TeamListBean> teamList) {
            this.teamList = teamList;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class TeamListBean {
            /**
             * liveTeam : {"fitInterest":2,"signature":"轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写","level":2,"threshold":"5000.00元","currentInterest":"1200047803.69元","everAuth":2,"names":"  转身离开  转身离开  Merlin  asda12312312312","sortid":2,"currmemberid":5,"headimgurl":"http://cdn.qilin99.cn/assets/img/banner/banner1.jpg","name":"赢在中国2","liveTime":"15:00-16:55  18:15-19:55","id":3,"livetime3":null,"livetime2":"18:15-19:55","temporary2":null,"needInterest":"8000.00元","livetime1":"15:00-16:55","status":1,"newImageUrl":"http://cdn.qilin99.cn/ctrade_cms/img/live_1481871631372"}
             * teamMemberList : [{"id":4,"username":"songjiang","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂","teamid":3,"nickname":"转身离开","token":"9761dba834829cf24f91f2de7f53404b","headimgurl":"http://cdn.qilin99.cn/test/2.jpg","jobtitle":"金牌分析师"},{"id":5,"username":"wuyong","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂","teamid":3,"nickname":"转身离开","token":"9761dba834829cf24f91f2de7f53404b","headimgurl":"http://7xqjs9.com2.z0.glb.qiniucdn.com/img/avatar/2_1453968172348","jobtitle":"金牌分析师"},{"id":8,"username":"raoxiang","passwd":"","brief":"我喊你一声,你敢答应吗","goodat":"打杂","teamid":3,"nickname":"Merlin","token":"2c67e758c73aeac13c56a58bb26e2629","headimgurl":"http://wp.500win.cn/ctrade/img/user.jpg","jobtitle":"金牌分析师"},{"id":9,"username":"asd","passwd":"","brief":"qwdqw                 ","goodat":"            asdasd                ","teamid":3,"nickname":"asda12312312312","token":"","headimgurl":"http://wp.500win.cn/ctrade/img/user.jpg","jobtitle":"dasd"}]
             */

            private LiveTeamBean liveTeam;
            private List<TeamMemberListBean> teamMemberList;

            public LiveTeamBean getLiveTeam() {
                return liveTeam;
            }

            public void setLiveTeam(LiveTeamBean liveTeam) {
                this.liveTeam = liveTeam;
            }

            public List<TeamMemberListBean> getTeamMemberList() {
                return teamMemberList;
            }

            public void setTeamMemberList(List<TeamMemberListBean> teamMemberList) {
                this.teamMemberList = teamMemberList;
            }

            public static class LiveTeamBean {
                /**
                 * fitInterest : 2
                 * signature : 轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写轻描淡写
                 * level : 2
                 * threshold : 5000.00元
                 * currentInterest : 1200047803.69元
                 * everAuth : 2
                 * names :   转身离开  转身离开  Merlin  asda12312312312
                 * sortid : 2
                 * currmemberid : 5
                 * headimgurl : http://cdn.qilin99.cn/assets/img/banner/banner1.jpg
                 * name : 赢在中国2
                 * liveTime : 15:00-16:55  18:15-19:55
                 * id : 3
                 * livetime3 : null
                 * livetime2 : 18:15-19:55
                 * temporary2 : null
                 * needInterest : 8000.00元
                 * livetime1 : 15:00-16:55
                 * status : 1
                 * newImageUrl : http://cdn.qilin99.cn/ctrade_cms/img/live_1481871631372
                 */

                private int fitInterest;
                private String signature;
                private int level;
                private String threshold;
                private String currentInterest;
                private int everAuth;
                private String names;
                private int sortid;
                private int currmemberid;
                private String headimgurl;
                private String name;
                private String liveTime;
                private int id;
                private Object livetime3;
                private String livetime2;
                private Object temporary2;
                private String needInterest;
                private String livetime1;
                private int status;
                private String newImageUrl;

                public int getFitInterest() {
                    return fitInterest;
                }

                public void setFitInterest(int fitInterest) {
                    this.fitInterest = fitInterest;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getThreshold() {
                    return threshold;
                }

                public void setThreshold(String threshold) {
                    this.threshold = threshold;
                }

                public String getCurrentInterest() {
                    return currentInterest;
                }

                public void setCurrentInterest(String currentInterest) {
                    this.currentInterest = currentInterest;
                }

                public int getEverAuth() {
                    return everAuth;
                }

                public void setEverAuth(int everAuth) {
                    this.everAuth = everAuth;
                }

                public String getNames() {
                    return names;
                }

                public void setNames(String names) {
                    this.names = names;
                }

                public int getSortid() {
                    return sortid;
                }

                public void setSortid(int sortid) {
                    this.sortid = sortid;
                }

                public int getCurrmemberid() {
                    return currmemberid;
                }

                public void setCurrmemberid(int currmemberid) {
                    this.currmemberid = currmemberid;
                }

                public String getHeadimgurl() {
                    return headimgurl;
                }

                public void setHeadimgurl(String headimgurl) {
                    this.headimgurl = headimgurl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLiveTime() {
                    return liveTime;
                }

                public void setLiveTime(String liveTime) {
                    this.liveTime = liveTime;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Object getLivetime3() {
                    return livetime3;
                }

                public void setLivetime3(Object livetime3) {
                    this.livetime3 = livetime3;
                }

                public String getLivetime2() {
                    return livetime2;
                }

                public void setLivetime2(String livetime2) {
                    this.livetime2 = livetime2;
                }

                public Object getTemporary2() {
                    return temporary2;
                }

                public void setTemporary2(Object temporary2) {
                    this.temporary2 = temporary2;
                }

                public String getNeedInterest() {
                    return needInterest;
                }

                public void setNeedInterest(String needInterest) {
                    this.needInterest = needInterest;
                }

                public String getLivetime1() {
                    return livetime1;
                }

                public void setLivetime1(String livetime1) {
                    this.livetime1 = livetime1;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getNewImageUrl() {
                    return newImageUrl;
                }

                public void setNewImageUrl(String newImageUrl) {
                    this.newImageUrl = newImageUrl;
                }
            }

            public static class TeamMemberListBean {
                /**
                 * id : 4
                 * username : songjiang
                 * passwd :
                 * brief : 我喊你一声,你敢答应吗
                 * goodat : 打杂
                 * teamid : 3
                 * nickname : 转身离开
                 * token : 9761dba834829cf24f91f2de7f53404b
                 * headimgurl : http://cdn.qilin99.cn/test/2.jpg
                 * jobtitle : 金牌分析师
                 */

                private int id;
                private String username;
                private String passwd;
                private String brief;
                private String goodat;
                private int teamid;
                private String nickname;
                private String token;
                private String headimgurl;
                private String jobtitle;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getPasswd() {
                    return passwd;
                }

                public void setPasswd(String passwd) {
                    this.passwd = passwd;
                }

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getGoodat() {
                    return goodat;
                }

                public void setGoodat(String goodat) {
                    this.goodat = goodat;
                }

                public int getTeamid() {
                    return teamid;
                }

                public void setTeamid(int teamid) {
                    this.teamid = teamid;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }

                public String getHeadimgurl() {
                    return headimgurl;
                }

                public void setHeadimgurl(String headimgurl) {
                    this.headimgurl = headimgurl;
                }

                public String getJobtitle() {
                    return jobtitle;
                }

                public void setJobtitle(String jobtitle) {
                    this.jobtitle = jobtitle;
                }
            }
        }

        public static class BannerBean {
            /**
             * id : 105
             * date : 2016-12-21 16:42:25
             * img_url : http://cdn.qilin99.cn/ctrade_cms/img/banner_1482077157619
             * action : url
             * url : http://wp.500win.cn/ctrade/activity/201612/lastpomp/lastpomp.html?redirect=1
             * title : 油市平衡恐黄粱一梦
             * type : home,live
             * sortId : 49
             * display : 1
             * time : -1
             * iconurl :
             * sharebrief :
             * isandroid : true
             * ish5 : true
             * isios : true
             * ispc : true
             * isweixin : true
             * activityname :
             * startdate :
             * enddate :
             * starttime :
             * endtime :
             */

            private int id;
            private String date;
            private String img_url;
            private String action;
            private String url;
            private String title;
            private String type;
            private int sortId;
            private int display;
            private int time;
            private String iconurl;
            private String sharebrief;
            private boolean isandroid;
            private boolean ish5;
            private boolean isios;
            private boolean ispc;
            private boolean isweixin;
            private String activityname;
            private String startdate;
            private String enddate;
            private String starttime;
            private String endtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getSortId() {
                return sortId;
            }

            public void setSortId(int sortId) {
                this.sortId = sortId;
            }

            public int getDisplay() {
                return display;
            }

            public void setDisplay(int display) {
                this.display = display;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getIconurl() {
                return iconurl;
            }

            public void setIconurl(String iconurl) {
                this.iconurl = iconurl;
            }

            public String getSharebrief() {
                return sharebrief;
            }

            public void setSharebrief(String sharebrief) {
                this.sharebrief = sharebrief;
            }

            public boolean isIsandroid() {
                return isandroid;
            }

            public void setIsandroid(boolean isandroid) {
                this.isandroid = isandroid;
            }

            public boolean isIsh5() {
                return ish5;
            }

            public void setIsh5(boolean ish5) {
                this.ish5 = ish5;
            }

            public boolean isIsios() {
                return isios;
            }

            public void setIsios(boolean isios) {
                this.isios = isios;
            }

            public boolean isIspc() {
                return ispc;
            }

            public void setIspc(boolean ispc) {
                this.ispc = ispc;
            }

            public boolean isIsweixin() {
                return isweixin;
            }

            public void setIsweixin(boolean isweixin) {
                this.isweixin = isweixin;
            }

            public String getActivityname() {
                return activityname;
            }

            public void setActivityname(String activityname) {
                this.activityname = activityname;
            }

            public String getStartdate() {
                return startdate;
            }

            public void setStartdate(String startdate) {
                this.startdate = startdate;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }
        }
    }

    private List<LiveListModel.ItemBean.TeamListBean> beans;
    private ILiveListPresernter iLiveListPresernter;

    @Override
    public void sendRequestToServer(Subscription subscription) {
        if (subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription= NetWork.getBaseApi()
                .getliveTeamlist("ctrade", 0.1)
                .map(new Func1<LiveListModel, List<LiveListModel.ItemBean.TeamListBean>>() {
                    @Override
                    public List<ItemBean.TeamListBean> call(LiveListModel liveListModel) {
                        return liveListModel.getItem().getTeamList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public LiveListModel(ILiveListPresernter iLiveListPresernter) {
        this.iLiveListPresernter = iLiveListPresernter;
    }

    @Override
    public void setMethod(int method) {

    }

    @Override
    public void cancelRequest() {

    }

    @Override
    public void setParam(Object... args) {

    }



    Observer<List<LiveListModel.ItemBean.TeamListBean>> observer=new Observer<List<LiveListModel.ItemBean.TeamListBean>>() {
        @Override
        public void onCompleted() {
            iLiveListPresernter.getDateSucceed(beans);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("TAG",e.getMessage());
        }

        @Override
        public void onNext(List<LiveListModel.ItemBean.TeamListBean> list) {
            beans=list;
        }
    };
}
