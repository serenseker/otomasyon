import java.sql.*;
import java.util.Scanner;
 
public class JavaOtomasyon {
    
   static Connection con;
   static Statement stmt;
   static String vtAd ;
    
    public static void main(String[] args) {
       vtAd = "deneme.db";
        /*veritabanı yoksa oluşturur varsa bağlanır*/
        try
        {
           yeniVtOlustur(vtAd);
        }catch(Exception e){ System.out.println(e);}
       
        
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        int secim;
        
        while(true)
        {
            System.out.println("*************");
            System.out.println("1.Ürünleri Listele Ve Teklif Ver");
            System.out.println("2.Ürün Ekle");
            System.out.println("3.Ürün Güncelle");
            System.out.println("4.Ürün Sil");
            System.out.println("5.Çıkış");
            System.out.print("Seçiminiz:");
            secim=scan.nextInt();
 
            System.out.println("*************");
            
            
            if(secim==1) {
                Listele();
                System.out.println("1. Teklif Ver");
                secim=scan.nextInt();
                switch (secim) {
                    case 1:
                    System.out.println("Ürün No :");
                    secim=scan.nextInt();
                    System.out.println("Teklifiniz :");
                    secim2=scan.nextInt();
                    String sorgu=String.format("update ogrenci set urunfiyat=%d where urunno=%d ", secim,secim2) ;
                    
                        break;
                
                    default:
                        break;
                }
            
            
            
            }
            if(secim==2) Ekle();
            if(secim==3) Guncelle();
            if(secim==4) Sil();
            if(secim==5) {
                try{
                    stmt.close();
                    con.close(); 
                }catch(Exception e){
                    System.out.println(e);
                }
                
                break;
            }            
        }
    }
    public static void yeniVtOlustur(String dosyaadi) throws ClassNotFoundException {
        try{   
         Class.forName("org.sqlite.JDBC");
         con = DriverManager.getConnection("jdbc:sqlite:deneme.db");
             stmt = con.createStatement();
             String sql = "CREATE TABLE if not exists URUN " +
                            "(URUNNO INT PRIMARY KEY     NOT NULL," +
                            " URUNAD  CHAR(50)    NOT NULL, " + 
                            " URUNSAHIBI   CHAR(50)     NOT NULL,"+
                            "URUNFIYAT INT NOT NULL)"; 
             stmt.executeUpdate(sql);
             System.out.println("Veritabanı Oluşturma Başarılı");
        } catch (SQLException e) {
         System.out.println(e.getMessage());
        }
    }
    public static void Listele()
    {
        try{
            
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from URUN"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    public static void Ekle()
    {
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        System.out.print("Yeni Ürün No     :");
        int yenino = scan.nextInt();
        System.out.print("Yeni Ürün Adı    :");
        String ad=scan.next();
        System.out.print("Yeni Ürün Soyadı :");
        String sahibi=scan.next();
        System.out.print("Yeni Ürün fiyat :");
        int fiyat=scan.next();
        try{
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("insert into ogrenci values( %d, '%s','%s','d')", yenino,ad,sahibi,fiyat);
            int ekleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıt Eklendi");
        }catch(Exception e){ System.out.println(e);}
        
            
    }
    
    public static void Guncelle()
    {
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        try{
            Listele();
            System.out.print("Yeni Ürün No     :");
            int yenino = scan.nextInt();
            System.out.print("Yeni Ürün Adı    :");
            String ad=scan.next();
            System.out.print("Yeni Ürün Soyadı :");
            String sahibi=scan.next();
            System.out.print("Yeni Ürün fiyat :");
            int fiyat=scan.next();
            
            String sorgu=String.format("update ogrenci set urunno=%d, urunad='%s',urunsahibi='%s',urunfiyat='%d' , where ogrno=%d ", yenino,ad,sahibi,fiyat,eskino) ;
            
            Statement stmt=con.createStatement(); 
            int guncelleme = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Güncellendi");
        }catch(Exception e){ System.out.println(e);}
    }
    
    public static void Sil()
    {
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        try{
            Listele();
            System.out.print("Öğrenci Numarasını Girin:");
            int eskino=scan.nextInt();  
            
            String sorgu=String.format("delete from ogrenci where urunno=%d",eskino);
            Statement stmt=con.createStatement(); 
            int silindi = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Silindi");
            
        }catch(Exception e){ System.out.println(e);}
    }
}