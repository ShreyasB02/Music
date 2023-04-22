package com.music.models;

import java.util.List;

// import com.music.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
@ToString
public class User {

  @Id
  private String id;

  // @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

  private String email;
  private String password;
  private String username;
  private List<String> queue;  
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getQueue() {
    return queue;
  }
  public void setQueue(List<String> queue) {
    this.queue = queue;
  }
  
  public String toString(User user){
    return user.getUsername() + " " + user.getEmail();
  }

}
