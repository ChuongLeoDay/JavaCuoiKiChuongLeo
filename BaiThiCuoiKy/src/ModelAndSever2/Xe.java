/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelAndSever2;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class Xe implements Serializable{
    String BienSo, LoaiXe, TenKhachHang, GioVaoBai, TenBaoVe;

    public Xe() {
    }

    public Xe(String BienSo, String LoaiXe, String TenKhachHang, String GioVaoBai, String TenBaoVe) {
        this.BienSo = BienSo;
        this.LoaiXe = LoaiXe;
        this.TenKhachHang = TenKhachHang;
        this.GioVaoBai = GioVaoBai;
        this.TenBaoVe = TenBaoVe;
    }

    public String getBienSo() {
        return BienSo;
    }

    public void setBienSo(String BienSo) {
        this.BienSo = BienSo;
    }

    public String getLoaiXe() {
        return LoaiXe;
    }

    public void setLoaiXe(String LoaiXe) {
        this.LoaiXe = LoaiXe;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getGioVaoBai() {
        return GioVaoBai;
    }

    public void setGioVaoBai(String GioVaoBai) {
        this.GioVaoBai = GioVaoBai;
    }

    public String getTenBaoVe() {
        return TenBaoVe;
    }

    public void setTenBaoVe(String TenBaoVe) {
        this.TenBaoVe = TenBaoVe;
    }
    
    
}
