package bench.git;

import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainGit {

	static final Logger logger = LoggerFactory.getLogger(MainGit.class);

	private static final String REPO = "./target/repo";
	private static final File FILE = new File(REPO);

	private static final String USER = "";
	private static final String PASS = "";

	private static final String URL = "git@github.com:carrot-garden/carrot-settings-config.git";

	public static void main(final String[] args) throws Exception {

		logger.debug("init");

		final FileRepositoryBuilder builder = new FileRepositoryBuilder();
		builder.setGitDir(FILE);
		builder.readEnvironment();
		builder.findGitDir();

		final Repository repo = builder.build();

		// logger.debug("branch=" + repo.getBranch());
		// logger.debug("state=" + repo.getRepositoryState());

		switch (repo.getRepositoryState()) {
		case BARE:
			break;
		case SAFE:
			break;
		default:
			logger.error("wrong state");
		}

		final Git git = make();
		logger.debug("state=" + git.getRepository().getRepositoryState());

		// git.checkout().setName(Constants.MASTER).call();
		// git.pull().setTimeout(10).call();

		git.fetch().call();

		git.checkout().setName("current-config").call();

		// runClone();

		// final Git git = Git.open(FILE);
		// final StatusCommand status = git.status();
		// final Status result = status.call();
		// logger.debug("result=" + result);

		// final Git git = new Git(repo);

		// final RevCommit commit =
		// git.commit().setMessage("test commit").call();

		// final RevTag tag = git.tag().setName("test_tag").call();

		// logger.debug("branch=" + repo.getBranch());
		// logger.debug("state=" + repo.getRepositoryState());

		logger.debug("done");

	}

	static Git make() {

		try {
			return makeClone();
		} catch (final Exception e) {
			logger.debug("clone : " + e.getMessage());
		}

		try {
			return makeOpen();
		} catch (final Exception e) {
			logger.debug("open : " + e.getMessage());
		}

		return null;
	}

	static Git makeClone() throws Exception {

		final CloneCommand clone = Git.cloneRepository();
		clone.setBare(false);
		clone.setDirectory(FILE).setURI(URL);

		final CredentialsProvider provider = //
		new UsernamePasswordCredentialsProvider(USER, PASS);

		clone.setCredentialsProvider(provider);

		return clone.call();

	}

	static Git makeOpen() throws Exception {

		return Git.open(FILE);

	}

}
