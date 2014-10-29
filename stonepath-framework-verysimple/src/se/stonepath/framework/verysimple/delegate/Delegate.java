package se.stonepath.framework.verysimple.delegate;

import se.stonepath.framework.verysimple.networking.SimpleStream;
import se.stonepath.framework.verysimple.networking.exception.SimpleStreamException;

/**
 * 
 * @author Per-Gustaf Stenberg
 *
 */


public interface Delegate {

	/**
	 * 
	 * @param stream
	 * @param params
	 */
	public void execute(SimpleStream stream, Object... params) throws SimpleStreamException;
}
