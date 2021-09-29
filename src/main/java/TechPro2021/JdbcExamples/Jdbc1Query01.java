package TechPro2021.JdbcExamples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Jdbc1Query01 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	//1)ilgili driver i yukle (tv nin fisini tak)	
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	//2)baglanti olustur (netflix bagla)	
		
	
	Connection con=	DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		
	//3) SQL komutlari icin bir Statement nesnesi oluştur (alan olusturduk, kumanda da kanal ayarlama)	
	
   Statement st=	con.createStatement();
   //4)SQL ifadeleri yazabilir ve calistirabiliriz. (kumanda da istediğimiz komuta basma. ses acma kanal degistirme)
		//(personel tablosundaki personel_id si 7369 olan personelin adini, maasini sorgula)
          ResultSet isim=     st.executeQuery("SELECT personel_isim, maas FROM personel WHERE personel_id=7369 ");
    //5) sonuclari aldik ve isledik
          while(isim.next()) {
        	 
        	System.out.print("Personel isim " +  isim.getString("personel_isim")+ " maas: "+isim.getInt("maas"));
          }
	}
}
