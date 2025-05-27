package ClassAux;

import ClassMother.ListProgram;
import Book.Book;
import User.User;
public class Inicialaizer extends ListProgram{


    /**/public static void inicialaizerUser() {
        if (User.numUser<10){
            String []name={"Ab","Abdou","Abdoula","Abdoulaye","Abdoulaye Edu","Abdoulaye Eduar","Abdoulaye Eduard","Abdoulaye Eduardo","Abdoulaye Eduardo Franci","Abdoulaye Eduardo Francisco"};
            String []pin={"1271","1232","2342","2132","3221","6556","8787","9054","9856","5698"};
            String []user={"ty4","4re","3ed","3dc","ds4","sd","sd","see","asa","hjv"};
            String []genUser={"Masculino","Masculino","Masculino","Masculino","Masculino","Masculino","Femenino","Femenino","Femenino","Masculino"};
            String []dateCad={"13h:03min:05seg  05/04/1973","29/03/1970","15/04/1986","10/06/1586","07/08/1886","20/09/1870","31/12/1890","03/03/1886","29/03/2007","13h:03min:05seg  17/11/2000"};

            for (int i = 0; i < 10; i++) {
                listName.add(i,name[i]);
                listPin.add(i,pin[i]);
                listUser.add(i,user[i]);
                listGenUser.add(i,genUser[i]);
                listDateCad.add(i,dateCad[i]);
                User.numUser++;
            }
        }
    }
}
