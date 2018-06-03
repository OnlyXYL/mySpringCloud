package top.wiklservice_dgraph.controller;

class Person {
        String name;
        String uid;
        Person() {}

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getUid() {
                return uid;
        }

        public void setUid(String uid) {
                this.uid = uid;
        }

        @Override
        public String toString() {
                return "Person{" +
                        "name='" + name + '\'' +
                        ", uid='" + uid + '\'' +
                        '}';
        }
}