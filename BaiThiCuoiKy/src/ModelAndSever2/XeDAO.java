/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModelAndSever2;

/**
 *
 * @author HP
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface XeDAO extends Remote {
    public String insert(String BienSo, String LoaiXe, String TenKhachHang, String GioVaoBai, String TenBaoVe) throws RemoteException;
    public boolean update(String BienSo, String LoaiXe, String TenKhachHang, String TenBaoVe) throws RemoteException;
    public boolean delete(String BienSo) throws RemoteException;

    /**
     *
     * @param BienSo
     * @return
     * @throws RemoteException
     */
    public Xe find(String BienSo) throws RemoteException;
    public ArrayList<Xe> show() throws RemoteException;
}
