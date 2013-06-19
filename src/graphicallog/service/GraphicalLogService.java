package graphicallog.service;

import java.util.List;

public interface GraphicalLogService {

	List<LogRecord> parseFile(String string);

}
