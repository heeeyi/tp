package seedu.address.storage.elderly;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyElderly;
import seedu.address.storage.JsonAppStorage;

/**
 * A class to access elderly data stored as a json file on the hard disk.
 */
public class JsonElderlyStorage extends JsonAppStorage<ReadOnlyElderly, FriendlyLink, JsonSerializableElderly>
        implements ElderlyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonElderlyStorage.class);

    public JsonElderlyStorage(Path filePath) {
        super(filePath);
    }

    @Override
    public Path getElderlyFilePath() {
        return super.getFilePath();
    }

    public Optional<ReadOnlyElderly> readElderly(FriendlyLink friendlyLink) throws DataConversionException {
        return super.read(JsonSerializableElderly.class, logger, friendlyLink);
    }

    /**
     * Similar to {@link ElderlyStorage#readElderly(FriendlyLink)}.
     *
     * @param filePath     location of the data. Cannot be null.
     * @param friendlyLink the cache to read the elderly data to.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyElderly> readElderly(Path filePath,
            FriendlyLink friendlyLink) throws DataConversionException {
        return super.read(filePath, JsonSerializableElderly.class, logger, friendlyLink);
    }

    @Override
    public void saveElderly(ReadOnlyElderly entity) throws IOException {
        saveElderly(entity, super.getFilePath());
    }

    /**
     * Similar to {@link #saveElderly(ReadOnlyElderly)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveElderly(ReadOnlyElderly entity, Path filePath) throws IOException {
        requireNonNull(entity);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableElderly(entity), filePath);
    }
}