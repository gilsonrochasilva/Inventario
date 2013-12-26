package br.com.inventario.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GilsonRocha on 21/12/13.
 */
@Entity
public class RegistroInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @Column(name = "produto_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Produto produto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_registro")
    private Date dataHoraRegistro = Calendar.getInstance().getTime();

    @Column(nullable = false)
    private Boolean importado = false;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(Date dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public Boolean getImportado() {
        return importado;
    }

    public void setImportado(Boolean importado) {
        this.importado = importado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroInventario that = (RegistroInventario) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}