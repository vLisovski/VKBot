package com.lisovski.VKbot.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hw_messages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HWMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "text")
    private String text;
    @Column(name = "peer_id")
    private Integer peer_id;

}
