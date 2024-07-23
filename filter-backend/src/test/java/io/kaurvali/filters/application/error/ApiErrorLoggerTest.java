package io.kaurvali.filters.application.error;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.List;

import static ch.qos.logback.classic.Level.toLevel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
class ApiErrorLoggerTest {

    private final Logger logger = (Logger) LoggerFactory.getLogger(ApiErrorLogger.class);

    private final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @InjectMocks
    private ApiErrorLogger target;

    @ParameterizedTest
    @CsvSource({"WARNING, WARN", "ERROR, ERROR"})
    void logError_categoryPassed_loggedCorrectly(ApiErrorCategory category, String expectedLoggingLevel) {
        var logsList = setupLoggerAppender();
        target.logError(category, "Error");

        assertThat(logsList, notNullValue());
        assertThat(logsList.size(), is(1));
        assertThat(logsList.getFirst().getLevel(), is(toLevel(expectedLoggingLevel)));
    }

    private List<ILoggingEvent> setupLoggerAppender() {
        listAppender.start();
        logger.addAppender(listAppender);
        return listAppender.list;
    }
}
