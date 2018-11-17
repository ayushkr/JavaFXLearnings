/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.Utils;

import java.io.File;

/**
 *
 * @author ayush
 */
public class UrlUtil {
    public static  String  getProperUrl(String urlBeforeCorrection){
    String urlAfterCorrection="";
        if(urlBeforeCorrection.startsWith("http://")||urlBeforeCorrection.startsWith("file://") ){
        urlAfterCorrection=urlBeforeCorrection;
  
        }
        else{
        urlAfterCorrection=new File(new File("").getAbsolutePath()+File.separator+urlBeforeCorrection).toURI()+"";
        }
        
        return urlAfterCorrection;
    }
    
    
    
  static void loadmp4(){
 File file = new File("barsandtone.mp4");
 String MEDIA_URL = file.toURI().toString();
}


}




