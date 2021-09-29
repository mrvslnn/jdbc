package TechPro2021.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery02{

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//BAGLANTI KUR
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");

		//SQL KOMUTLARI İÇİN BİR STATEMENT(ALAN) NEsesSNESİ OLUSTUR
		Statement st = con.createStatement();

		ResultSet tablo1= st.executeQuery("SELECT * FROM bolumler");

		while (tablo1.next()) {

		    System.out.println(tablo1.getInt(1) + "\t"+ tablo1.getString(2) +"\t"+ tablo1.getString(3));
		}
		System.out.println("=================================");
	
//ornek2 satis ve muhasebe bolumlerinde calian  personelinni ve maaslarını  maas ters sirali olarak listeleyiniz
String a="SELECT personel_isim, maas"
+" FROM personel"
		+ " WHERE bolum_id IN (10,30)"
+ " ORDER BY maas DESC";

	ResultSet tablo2=st.executeQuery(a); 
	
	while(tablo2.next()) {
		System.out.println( tablo2.getString(1)+ "    \t"+ tablo2.getInt(2));
	}
	
	System.out.println("========================================0");
	/*  ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini
    ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa
    bile bolum ismi gosterilmelidir.   */
  System.out.println("=====================================0");
  ResultSet sonuc1= st.executeQuery("SELECT bolum_isim , personel_isim , maas"
          + " FROM bolumler b"
          + " LEFT JOIN personel p"
          + " ON b.bolum_id=p.bolum_id"
          + " ORDER BY bolum_isim , maas" );
  while (sonuc1.next()) {
      System.out.println(sonuc1.getString(1)+"    \t" +sonuc1.getString(2) +"     \t" + sonuc1.getInt(3));
  }
  System.out.println("=====================================");

  /*=======================================================================
    ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
*/
		
		ResultSet sonuc2 = st.executeQuery("SELECT bolum_isim, personel_isim, maas"
				+ " FROM personel p"
				+ " FULL JOIN bolumler b "
				+ " ON b.bolum_id=p.bolum_id"
				+ " ORDER BY maas DESC"
				+ " FETCH NEXT 10 ROWS ONLY");
		
		while(sonuc2.next()) {
			
		System.out.println(	sonuc2.getString(1)+"\t\t"+sonuc2.getString(2) + "\t\t"+sonuc2.getInt(3));
		}
		
		con.close();
		st.close();
		tablo1.close();
		tablo2.close();
		sonuc1.close();
		sonuc2.close();
		

	}
}
