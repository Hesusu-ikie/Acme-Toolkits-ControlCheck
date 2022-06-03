package acme.entities.peman;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.inventions.Invention;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import spam.detector.SpamDetector;

@Entity
@Getter
@Setter
public class Peman extends AbstractEntity {

	// Serialisation identifier ----------------------------------------
	
	protected static final long serialVersionUID		= 1L;
	
	// Atributes -------------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "^\\w{2}\\d{2}\\w{2}-[0-9]{6}$")
	protected String			code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				creationMoment;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			subject;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			statement;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endTime;
	
	@NotNull
	@Valid
	protected Money				expenditure;
	
	@URL
	protected String			additionalInfo; 
	
	// Derived attributes ----------------------------------------------
	
	public boolean isSpam(final SystemConfiguration systemConfiguration) {
			
			final String text = this.getSubject() + "\n" + this.getStatement();
		return SpamDetector.isSpam(text, systemConfiguration.getWeakSpamTerms(), systemConfiguration.getStrongSpamTerms(), systemConfiguration.getStrongSpamTermsThreshold(), systemConfiguration.getWeakSpamTermsThreshold());
	
	}

	
	// Relationships ---------------------------------------------------
	
	@Valid
	@OneToOne
	protected Invention				invention;
	
}
