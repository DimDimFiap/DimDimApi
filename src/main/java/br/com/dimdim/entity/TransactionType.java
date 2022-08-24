package br.com.dimdim.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_dim_transaction_type")

public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_type")
    private Integer id;

    @Column(name = "nm_type", length = 20)
    private String type;

    @OneToOne(mappedBy = "cd_type")
    private TransactionType transactionType;

    public TransactionType() {
    }

    public TransactionType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}