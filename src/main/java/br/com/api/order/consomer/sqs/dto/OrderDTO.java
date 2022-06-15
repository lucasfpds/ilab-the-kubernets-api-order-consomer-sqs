package br.com.api.order.consomer.sqs.dto;

import java.sql.Timestamp;

public class OrderDTO {
    private Integer idAdmin;
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private String description;
    private Integer totalValue;
    private Timestamp ordersDate;
    private String status = "aberto";
    private String statusEmail = "não enviado";

    public OrderDTO() {

    }

    public OrderDTO(Integer idAdmin, Integer idUser,
            String nameUser, String emailUser, String description,
            Integer totalValue, Timestamp ordersDate) {
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.description = description;
        this.totalValue = totalValue;
        this.ordersDate = ordersDate;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    public Timestamp getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Timestamp ordersDate) {
        this.ordersDate = ordersDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(String statusEmail) {
        this.statusEmail = statusEmail;
    }
}
