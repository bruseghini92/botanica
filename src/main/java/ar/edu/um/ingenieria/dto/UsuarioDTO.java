package ar.edu.um.ingenieria.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

public class UsuarioDTO {

	private Integer id;
	@NotNull
	private String user;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 8, max = 16)
	private String password;

	private RolDTO rol;

	private PersonaDTO persona;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date lastPasswordResetDate;

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", user=" + user + ", email=" + email + ", password=" + password + ", rol="
				+ rol + ", personaDTO=" + persona + ", lastPasswordResetDate=" + lastPasswordResetDate + "]";
	}

	public UsuarioDTO() {
		super();
	}
}
