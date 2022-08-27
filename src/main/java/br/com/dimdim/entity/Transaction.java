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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_type")
    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(String title, Double value) {
        this.title = title;
        this.value = value;
    }

    public Transaction(Integer id, String title, Double value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public Transaction(Integer id, String title, Double value, TransactionType transactionType) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.transactionType = transactionType;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", value=" + value +
                ", transactionType=" + transactionType +
                '}';
    }
}
