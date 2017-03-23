//package myapps.wycoco.com.yourfaceseemsattendance;
//
//import android.content.AsyncTaskLoader;
//import android.content.Context;
//
//import java.util.ArrayList;
//
//import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
//
///**
// * Created by dell on 3/24/2017.
// */
//
//public class Loader extends AsyncTaskLoader<ArrayList<SubjectModel>>{
//
//    private String mUrl;
//
//    public Loader(Context context, String mUrl) {
//        super(context);
//        this.mUrl = mUrl;
//    }
//
//    @Override
//    protected void onStartLoading() {
//        forceLoad();
//    }
//
//
//    @Override
//    public ArrayList<SubjectModel> loadInBackground() {
//        if(mUrl == null){
//            return null;
//        }
//
//        ArrayList<SubjectModel> articles = QueryUtils.fetchNewsData(mUrl);
//        return articles;
//    }
//}
