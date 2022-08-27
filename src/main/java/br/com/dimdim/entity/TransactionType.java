package br.com.dimdim.entity;

import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_dim_transaction_type")

public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_type")
    private Integer id;

    @Column(name = "nm_type", length = 20)
    private String type;

    @OneToMany(mappedBy = "transactionType" , cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionType() {

    }

    public TransactionType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public TransactionType(String type) {
        this.type = type;
    }

    public void addTransaction(Transaction transaction) {
        transaction.setTransactionType(this);
        this.transactions.add(transaction);
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}