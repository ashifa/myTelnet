package telnet.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TelnetService {
	public List<List<String>> getNewVersion(Collection<String> CMDlist);

	public List<List<String>> getOldVersion();

	public Map<String, String> getTargetMap();

	public void setTargetMap(Map<String, String> targetMap);

	public Map<String, String> getCMDMap();

	public void setCMDMap(Map<String, String> cMDMap);
}
