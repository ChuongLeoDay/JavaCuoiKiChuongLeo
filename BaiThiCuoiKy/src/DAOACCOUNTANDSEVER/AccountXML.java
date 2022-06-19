/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOACCOUNTANDSEVER;

import static SQLInfor.DatabaseInfo.connectionURL;
import static SQLInfor.DatabaseInfo.password;
import static SQLInfor.DatabaseInfo.userName;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author HP
 */
public class AccountXML {
    public void insertXML() throws SQLException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
//       Goi ve SQL 
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        String sql = "select * from Account";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
//       Dung XML
        String ResultFinal = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BaiThiCuoiKy\\src\\UserXML\\User.xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
//Root element
        Element root = doc.createElement("quanli");
        doc.appendChild(root);
        while(rs.next()) {
            Account account = new Account();
            account.setTenDangNhap(rs.getString(1));
            account.setMatKhau(rs.getString(2));
            
            Element NguoiDung = doc.createElement("NguoiDung");
            root.appendChild(NguoiDung);
            
            Element tenTK = doc.createElement("TenTK");
            tenTK.appendChild(doc.createTextNode(account.getTenDangNhap().trim()));
            NguoiDung.appendChild(tenTK);
            
            Element MatKhau = doc.createElement("MatKhau");
            MatKhau.appendChild(doc.createTextNode(account.getMatKhau().trim()));
            NguoiDung.appendChild(MatKhau);
        }
            TransformerFactory tranform = TransformerFactory.newInstance();
            Transformer tranformer = tranform.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult streamrs = new StreamResult(new File(ResultFinal));
            tranformer.transform(source, streamrs);
    }
    
//    Doc XML
    public ArrayList<Account> showXML() throws ParserConfigurationException, SAXException, IOException {
        ArrayList<Account> list = new ArrayList<>();
        String url = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BaiThiCuoiKy\\src\\UserXML\\User.xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(url);
        
            Element NguoiDung;
            NguoiDung = doc.getDocumentElement();
            NodeList liTenTK = NguoiDung.getElementsByTagName("TenTK");
            NodeList liMK = NguoiDung.getElementsByTagName("MatKhau");


            
            for (int i = 0; i < liTenTK.getLength(); i++) {
                Account acc1 = new Account();
                acc1.setTenDangNhap(liTenTK.item(i).getTextContent());
                acc1.setMatKhau(liMK.item(i).getTextContent());

               
                
                list.add(acc1);
            }
        return list;
    }
}
