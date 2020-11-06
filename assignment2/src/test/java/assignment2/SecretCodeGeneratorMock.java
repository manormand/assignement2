package assignment2;

import java.util.List;

public class SecretCodeGeneratorMock extends SecretCodeGenerator {
    List<String> codes;
    int nextCodeIdx = 0;

    SecretCodeGeneratorMock() {
        super(null);
    }

    SecretCodeGeneratorMock(List<String> codes) {
        super(null);
        this.codes = codes;
    }

    // Use this method for each game only once.
    @Override
    public String getNewSecretCode() {
        if (nextCodeIdx < codes.size()) {
            String ret = codes.get(nextCodeIdx);
            nextCodeIdx += 1;
            return ret;
        }
        throw new RuntimeException("More calls to getNewSecretCode than expected.");
    }

    /**
     * @return whether getNewSecretCode has been called as many times as there are
     * codes in this.codes
     */
    boolean isDone() {
        return nextCodeIdx == codes.size();
    }

    /**
     * Reset the mock.
     */
    void reset() {
        nextCodeIdx = 0;
        codes.clear();
    }
}
