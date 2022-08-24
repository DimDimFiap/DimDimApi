package br.com.dimdim.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_dim_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_transaction")
    private Integer id;

    @Column(name = "nm_transaction", nullable = false, length = 100)
    private String title;

    @Column(name = "vl_transaction", nullable = false, scale = 2, length = 10)
    private Double value;

    @OneToOne
    @JoinColumn(name = "cd_type", referencedColumnName = "cd_type")
    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(Integer id, String title, Double value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
