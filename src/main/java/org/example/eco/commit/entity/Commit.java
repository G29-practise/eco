package org.example.eco.commit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.user.entity.User;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Commit {
    @Id
    private UUID id;
    private String commit;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
