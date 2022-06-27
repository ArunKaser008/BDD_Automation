package com.enginesApi;

public class AuthorizationPojo {

        public String clientID;
        public String clientSecret;
        public String password;
        public String userName;
        

        /**
         * No args constructor for use in serialization
         *
         */
        public AuthorizationPojo() {
        }

        /**
         *
         * @param password
         * @param userName
         */
        public AuthorizationPojo(String userName, String password) {
            super();
            this.userName = userName;
            this.password = password;
        }
}
