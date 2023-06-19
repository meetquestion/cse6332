import com.baomidou.mybatisplus.core.toolkit.support.BiIntFunction;
import com.bupt.uta.common.R;
import com.bupt.uta.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;

public class Test {
    @org.junit.Test
    public void testIsValidPhoneNumber(){
        System.out.println(Utils.isValidPhoneNumber("1737920826"));
    }
    @org.junit.Test
    public void testValidEmail(){
        System.out.println(Utils.ValidateEmailFormat("sdfsdfsdf@qawe.sdf"));
    }

    public static void main(String[] args) throws ParseException {
        /*Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date date = formatter.parse(dateString);*/



    }
}
