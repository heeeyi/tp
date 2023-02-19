package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class MedicalQualificationTagTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalQualificationTag(
                null, MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12)));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidMedicalQualificationTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalQualificationTag(
                invalidMedicalQualificationTagName, MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12)));
    }
    @Test
    public void getQualificationLevel() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag(
                "CPR", MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12));

        assertEquals(MedicalQualificationTag.SkillLevel.ADVANCED,
                medicalQualificationTag.getQualificationLevel());
    }

    @Test
    public void getExpiryDate() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag(
                "CPR", MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12));

        assertEquals(LocalDate.of(2040, 5, 12),
                medicalQualificationTag.getExpiryDate());
    }

    @Test
    public void isExpired_expired_returnTrue() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag(
                "CPR", MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2000, 5, 12));

        assertTrue(medicalQualificationTag.isExpired());
    }

    @Test
    public void changeQualificationLevel() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag(
                "CPR", MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12));

        medicalQualificationTag.setQualificationLevel(MedicalQualificationTag.SkillLevel.BASIC);
        assertEquals(MedicalQualificationTag.SkillLevel.BASIC,
                medicalQualificationTag.getQualificationLevel());
    }

    @Test
    public void changeExpiryDate() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag(
                "CPR", MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12));

        medicalQualificationTag.setExpiryDate(LocalDate.of(2050, 7, 1));
        assertEquals(LocalDate.of(2050, 7, 1),
                medicalQualificationTag.getExpiryDate());
    }

    @Test
    public void testFullStringConversion() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag(
                "CPR", MedicalQualificationTag.SkillLevel.ADVANCED,
                LocalDate.of(2040, 5, 12));

        String fullString = "[CPR] ADVANCED 2040-05-12";
        assertEquals(fullString, medicalQualificationTag.toFullString());
    }
}