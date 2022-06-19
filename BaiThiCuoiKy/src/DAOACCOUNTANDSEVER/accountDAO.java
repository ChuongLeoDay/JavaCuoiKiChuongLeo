/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOACCOUNTANDSEVER;

/**
 *
 * @author HP
 */

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface accountDAO extends Remote{
    public String insert(String TenDangNhap, String MatKhau) throws RemoteException;
    public Account find(String TenDangNhap, String MatKhau) throws RemoteException;
}
