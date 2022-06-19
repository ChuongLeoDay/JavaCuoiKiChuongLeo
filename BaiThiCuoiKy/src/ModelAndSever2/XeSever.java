/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelAndSever2;

/**
 *
 * @author HP
 */


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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class XeSever extends UnicastRemoteObject implements XeDAO{
    public XeSever() throws RemoteException {
    }

    @Override
    public String insert(String BienSo, String LoaiXe, String TenKhachHang, String GioVaoBai, String TenBaoVe) throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "Insert into Xe values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            int rc = 0;
            ps.setString(1, BienSo);
            ps.setString(2, LoaiXe);
            ps.setString(3, TenKhachHang);
            ps.setString(4, GioVaoBai);
            ps.setString(5, TenBaoVe);
            
            rc = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Đã lưu";
    }
    
    @Override
    public boolean update(String BienSo, String LoaiXe, String TenKhachHang, String TenBaoVe) throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "update Xe set BienSo = ?, LoaiXe = ?, TenKhachHang = ?, TenBaoVe = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            int rc = 0;
            ps.setString(1, BienSo);
            ps.setString(2, LoaiXe);
            ps.setString(3, TenKhachHang);
            ps.setString(4, TenBaoVe);
            
            rc = ps.executeUpdate();          
        } catch (SQLException ex) {
            Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    @Override
    public boolean delete(String BienSo) throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "delete from Xe where BienSo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            int rc = 0;
            ps.setString(1, BienSo);
            rc = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    @Override
    public Xe find(String BienSo) throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "select * from Xe where BienSo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, BienSo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Xe xe = new Xe();
                
                xe.setBienSo(rs.getString(1));
                xe.setLoaiXe(rs.getString(2));
                xe.setTenKhachHang(rs.getString(3));
                xe.setGioVaoBai(rs.getString(4));
                xe.setTenBaoVe(rs.getString(5));
                
                return xe;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ArrayList<Xe> show() throws RemoteException {
        ArrayList<Xe> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "select * from Xe";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Xe xe = new Xe();
                
                xe.setBienSo(rs.getString(1));
                xe.setLoaiXe(rs.getString(2));
                xe.setTenKhachHang(rs.getString(3));
                xe.setGioVaoBai(rs.getString(4));
                xe.setTenBaoVe(rs.getString(5));
                
                list.add(xe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

        public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(500);
            try {
                Naming.rebind("rmi://localhost:500/XeSever", new XeSever());
            } catch (MalformedURLException ex) {
                Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("Server Xe is ready");
        } catch (RemoteException ex) {
            Logger.getLogger(XeSever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
