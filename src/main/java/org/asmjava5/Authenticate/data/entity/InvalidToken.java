package org.asmjava5.Authenticate.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "INVALIDATED_TOKEN")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvalidToken {
    @Id
    @Column(name = "id")
    private String invalidTokenId;

    @Column(name = "exp_time")
    private Timestamp expTime;
}
