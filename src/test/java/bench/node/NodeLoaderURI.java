package bench.node;

public enum NodeLoaderURI {

	BASE("base"), // defined
	CLAP("clap"), // class path

	FILE("file"), // local

	HOME("home"), // ${user.home}
	WORK("work"), // ${user.dir}

	S3("s3"), //
	SDB("sdb"), //

	FTP("ftp"), //
	HTTP("http"), //
	HTTPS("https"), //

	;

	public final String scheme;

	NodeLoaderURI(final String scheme) {
		this.scheme = scheme;
	}

}
