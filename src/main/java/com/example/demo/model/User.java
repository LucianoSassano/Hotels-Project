package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name="users")
public class User {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    private String name;

    private String address;

    private String email;

    private Integer phone;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @NotNull
    private Date createAt;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @NotNull
    private Date updateAt;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @NotNull
    private Date deleteAt;

    private Integer idRole;

    @OneToMany(mappedBy = "users")
    private List<CreditCard> cards = new ArrayList<>();

}
