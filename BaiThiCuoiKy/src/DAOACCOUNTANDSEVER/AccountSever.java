/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOACCOUNTANDSEVER;

import static SQLInfor.DatabaseInfo.connectionURL;
import static SQLInfor.DatabaseInfo.password;
import static SQLInfor.DatabaseInfo.userName;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author HP
 */
public class AccountSever extends UnicastRemoteObject implements accountDAO{
    
    public AccountSever() throws RemoteException{
    }

    @Override
    public String insert(String TenDangNhap, String MatKhau) throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "Insert into Account values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TenDangNhap);
            ps.setString(2, MatKhau);
            int rc = 0;
            rc = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountSever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Đã đăng kí!!!!";
    }
    
    @Override
    public Account find(String TenDangNhap, String MatKhau) throws RemoteException{
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "select * from Account where TenTaiKhoan = ? and MatKhau = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TenDangNhap);
            ps.setString(2, MatKhau);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Account acc = new Account();
                acc.setTenDangNhap(rs.getString(1));
                acc.setMatKhau(rs.getString(2));
                return acc;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountSever.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
                                                                                                  
        public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(400);
            try {
                Naming.rebind("rmi://localhost:400/AccountSever", new AccountSever());
            } catch (MalformedURLException ex) {
                Logger.getLogger(AccountSever.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("Server ready");
        } catch (RemoteException ex) {
            Logger.getLogger(AccountSever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
