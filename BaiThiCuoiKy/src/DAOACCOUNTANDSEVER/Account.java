/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOACCOUNTANDSEVER;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class Account implements Serializable{
    String TenDangNhap, MatKhau;

    public Account() {
    }

    public Account(String TenDangNhap, String MatKhau) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    
    
}
