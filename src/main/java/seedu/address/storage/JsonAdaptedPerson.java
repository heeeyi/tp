package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Person;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
public abstract class JsonAdaptedPerson {
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String nric;
    private final String age;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedAvailableDate> availableDates = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("nric") String nric, @JsonProperty("age") String age,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("tagged") List<JsonAdaptedAvailableDate> availableDates) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nric = nric;
        this.age = age;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (availableDates != null) {
            this.availableDates.addAll(availableDates);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        nric = source.getNric().value;
        age = source.getAge().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        availableDates.addAll(source.getAvailableDates().stream()
                .map(JsonAdaptedAvailableDate::new)
                .collect(Collectors.toList()));
    }

    public Nric getModelNric(String missingFieldMessageFormat) throws IllegalValueException {
        if (nric == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(nric);
    }

    public Age getModelAge(String missingFieldMessageFormat) throws IllegalValueException {
        if (age == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(age);
    }

    protected Address getModelAddress(String missingFieldMessageFormat) throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    protected Email getModelEmail(String missingFieldMessageFormat) throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    protected Phone getModelPhone(String missingFieldMessageFormat) throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    protected Name getModelName(String missingFieldMessageFormat) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    protected Set<Tag> getTagSet(FriendlyLink friendlyLink) throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType(friendlyLink));
        }
        return new HashSet<>(personTags);
    }

    protected Set<AvailableDate> getAvailableDateSet() throws IllegalValueException {
        final List<AvailableDate> personAvailableDates = new ArrayList<>();
        for (JsonAdaptedAvailableDate date : availableDates) {
            personAvailableDates.add(date.toModelType());
        }
        return new HashSet<>(personAvailableDates);
    }


}
