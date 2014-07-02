package se.stonepath.framework.verysimple.delegate;

import se.stonepath.framework.verysimple.networking.SimpleStream;




public interface Delegate {

	public void execute(SimpleStream stream, Object... params);
}
