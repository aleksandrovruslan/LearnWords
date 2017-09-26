package com.aleksandrov.View;

import com.aleksandrov.Model.ModelAdd;
import com.aleksandrov.Model.ModelLearn;
import com.aleksandrov.Model.ModelMain;
import com.aleksandrov.View.SWING.ViewSWINGAdd;
import com.aleksandrov.View.SWING.ViewSWINGLearn;
import com.aleksandrov.View.SWING.ViewSWINGMain;
import com.aleksandrov.View.Web.ViewWebAdd;
import com.aleksandrov.View.Web.ViewWebLearn;
import com.aleksandrov.View.Web.ViewWebMain;

public class ViewCreator {

    public static void createView(String str, ModelMain model){
        if(str.equals("SWING")){
            new ViewSWINGMain(model);
        }else if(str.equals("Web")){
            new ViewWebMain(model);
        }
    }

    public static void createView(String str, ModelLearn model){
        if(str.equals("SWING")){
            new ViewSWINGLearn(model);
        }else if(str.equals("Web")){
            new ViewWebLearn(model);
        }
    }

    public static void createView(String str, ModelAdd model){
        if(str.equals("SWING")){
            new ViewSWINGAdd(model);
        }else if(str.equals("Web")){
            new ViewWebAdd(model);
        }
    }

}
