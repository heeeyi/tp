package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.VolunteerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddVolunteerCommand}.
 */
public class AddVolunteerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
    }

    @Test
    public void execute_newVolunteer_success() {
        Volunteer validVolunteer = new VolunteerBuilder().build();

        Model expectedModel = new ModelManager(model.getFriendlyLink(), new UserPrefs());
        expectedModel.addVolunteer(validVolunteer);

        assertCommandSuccess(new AddVolunteerCommand(validVolunteer), model,
                String.format(AddVolunteerCommand.MESSAGE_SUCCESS, validVolunteer), expectedModel);
    }

    @Test
    public void execute_duplicateVolunteer_throwsCommandException() {
        Volunteer volunteerInList = model.getFriendlyLink().getVolunteerList().get(0);
        assertCommandFailure(new AddVolunteerCommand(volunteerInList), model,
                Messages.MESSAGE_DUPLICATE_VOLUNTEER);
    }

}