package org.oop.app;

import jakarta.persistence.*;

import lombok.*;
    @EqualsAndHashCode(callSuper = true)
    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Player_personal_data extends Player_profile{
        private String name;
        private String email;
        private String phone_number;

        public Player_personal_data(int idplayer,String username,String name, String email, String phone_number) {
            super(idplayer,username);
            this.name = name;
            this.email = email;
            this.phone_number = phone_number;

        }
    }
