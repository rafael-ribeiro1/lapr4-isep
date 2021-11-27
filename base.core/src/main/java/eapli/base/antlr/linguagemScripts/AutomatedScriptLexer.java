// Generated from C:/Users/Rafael/Desktop/LEI - ISEP/2Ano 2Semestre/LAPR4/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/linguagemScripts\AutomatedScript.g4 by ANTLR 4.9.1
package eapli.base.antlr.linguagemScripts;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AutomatedScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NUM=3, PERCENTAGEM=4, SOMA_SUB=5, MUL_DIV=6, BARRA=7, 
		SCRIPT=8, AND=9, OR=10, IGUAL=11, EMAIL=12, OPERADOR_COMPARAR=13, IF=14, 
		WORD=15, VIRGULA=16, DOLLAR=17, ASPAS=18, CHAVETA_ENTRADA=19, CHAVETA_FECHAR=20, 
		PARENTESE_CURVO_ABRIR=21, PARENTESE_CURVO_FECHAR=22, PONTO_VIRGULA=23, 
		XML=24, FILE_PATH=25, WS=26, ERR_CHAR=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "NUM", "PERCENTAGEM", "SOMA_SUB", "MUL_DIV", "BARRA", 
			"SCRIPT", "AND", "OR", "IGUAL", "EMAIL", "OPERADOR_COMPARAR", "IF", "WORD", 
			"VIRGULA", "DOLLAR", "ASPAS", "CHAVETA_ENTRADA", "CHAVETA_FECHAR", "PARENTESE_CURVO_ABRIR", 
			"PARENTESE_CURVO_FECHAR", "PONTO_VIRGULA", "XML", "FILE_PATH", "WS", 
			"ERR_CHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'sendEmail'", "'readFile'", null, "'%'", null, null, "'/'", "'SCRIPT'", 
			"'AND'", "'OR'", "'='", null, null, "'if'", null, "','", "'$'", "'\"'", 
			"'{'", "'}'", "'('", "')'", "';'", "'.xml'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "NUM", "PERCENTAGEM", "SOMA_SUB", "MUL_DIV", "BARRA", 
			"SCRIPT", "AND", "OR", "IGUAL", "EMAIL", "OPERADOR_COMPARAR", "IF", "WORD", 
			"VIRGULA", "DOLLAR", "ASPAS", "CHAVETA_ENTRADA", "CHAVETA_FECHAR", "PARENTESE_CURVO_ABRIR", 
			"PARENTESE_CURVO_FECHAR", "PONTO_VIRGULA", "XML", "FILE_PATH", "WS", 
			"ERR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public AutomatedScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AutomatedScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00c1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\5\4N\n\4\3\4\6\4Q\n\4\r"+
		"\4\16\4R\3\4\3\4\6\4W\n\4\r\4\16\4X\5\4[\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\7\5\7d\n\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\6\rz\n\r\r\r\16\r{\3\r\3\r\6\r\u0080\n\r\r\r"+
		"\16\r\u0081\3\r\3\r\6\r\u0086\n\r\r\r\16\r\u0087\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0095\n\16\3\17\3\17\3\17\3\20\6\20"+
		"\u009b\n\20\r\20\16\20\u009c\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\6"+
		"\32\u00b5\n\32\r\32\16\32\u00b6\3\33\6\33\u00ba\n\33\r\33\16\33\u00bb"+
		"\3\33\3\33\3\34\3\34\2\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\35\3\2\13\3\2\62;\4\2--//\t\2\'\'--/\60\62;C\\aac|\6\2"+
		"/\60\62;C\\c|\4\2C\\c|\4\2>>@@\t\2##\62<AAC\\aac|\u00c2\u0101\6\2\60\61"+
		"C\\^^c|\5\2\13\f\17\17\"\"\2\u00d0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\39\3\2\2\2\5C\3\2\2\2\7M\3\2\2\2\t\\\3\2\2\2\13^\3\2"+
		"\2\2\rc\3\2\2\2\17e\3\2\2\2\21g\3\2\2\2\23n\3\2\2\2\25r\3\2\2\2\27u\3"+
		"\2\2\2\31w\3\2\2\2\33\u0094\3\2\2\2\35\u0096\3\2\2\2\37\u009a\3\2\2\2"+
		"!\u009e\3\2\2\2#\u00a0\3\2\2\2%\u00a2\3\2\2\2\'\u00a4\3\2\2\2)\u00a6\3"+
		"\2\2\2+\u00a8\3\2\2\2-\u00aa\3\2\2\2/\u00ac\3\2\2\2\61\u00ae\3\2\2\2\63"+
		"\u00b4\3\2\2\2\65\u00b9\3\2\2\2\67\u00bf\3\2\2\29:\7u\2\2:;\7g\2\2;<\7"+
		"p\2\2<=\7f\2\2=>\7G\2\2>?\7o\2\2?@\7c\2\2@A\7k\2\2AB\7n\2\2B\4\3\2\2\2"+
		"CD\7t\2\2DE\7g\2\2EF\7c\2\2FG\7f\2\2GH\7H\2\2HI\7k\2\2IJ\7n\2\2JK\7g\2"+
		"\2K\6\3\2\2\2LN\7/\2\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OQ\t\2\2\2PO\3\2\2"+
		"\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2SZ\3\2\2\2TV\7\60\2\2UW\t\2\2\2VU\3\2"+
		"\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZT\3\2\2\2Z[\3\2\2\2[\b\3"+
		"\2\2\2\\]\7\'\2\2]\n\3\2\2\2^_\t\3\2\2_\f\3\2\2\2`d\7,\2\2ad\5\17\b\2"+
		"bd\7%\2\2c`\3\2\2\2ca\3\2\2\2cb\3\2\2\2d\16\3\2\2\2ef\7\61\2\2f\20\3\2"+
		"\2\2gh\7U\2\2hi\7E\2\2ij\7T\2\2jk\7K\2\2kl\7R\2\2lm\7V\2\2m\22\3\2\2\2"+
		"no\7C\2\2op\7P\2\2pq\7F\2\2q\24\3\2\2\2rs\7Q\2\2st\7T\2\2t\26\3\2\2\2"+
		"uv\7?\2\2v\30\3\2\2\2wy\7$\2\2xz\t\4\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2"+
		"{|\3\2\2\2|}\3\2\2\2}\177\7B\2\2~\u0080\t\5\2\2\177~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0085\7\60\2\2\u0084\u0086\t\6\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3"+
		"\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008a\7$\2\2\u008a\32\3\2\2\2\u008b\u008c\7?\2\2\u008c\u0095\7?\2\2\u008d"+
		"\u008e\7>\2\2\u008e\u0095\7@\2\2\u008f\u0095\t\7\2\2\u0090\u0091\7>\2"+
		"\2\u0091\u0095\7?\2\2\u0092\u0093\7@\2\2\u0093\u0095\7?\2\2\u0094\u008b"+
		"\3\2\2\2\u0094\u008d\3\2\2\2\u0094\u008f\3\2\2\2\u0094\u0090\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0095\34\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7h\2\2"+
		"\u0098\36\3\2\2\2\u0099\u009b\t\b\2\2\u009a\u0099\3\2\2\2\u009b\u009c"+
		"\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d \3\2\2\2\u009e"+
		"\u009f\7.\2\2\u009f\"\3\2\2\2\u00a0\u00a1\7&\2\2\u00a1$\3\2\2\2\u00a2"+
		"\u00a3\7$\2\2\u00a3&\3\2\2\2\u00a4\u00a5\7}\2\2\u00a5(\3\2\2\2\u00a6\u00a7"+
		"\7\177\2\2\u00a7*\3\2\2\2\u00a8\u00a9\7*\2\2\u00a9,\3\2\2\2\u00aa\u00ab"+
		"\7+\2\2\u00ab.\3\2\2\2\u00ac\u00ad\7=\2\2\u00ad\60\3\2\2\2\u00ae\u00af"+
		"\7\60\2\2\u00af\u00b0\7z\2\2\u00b0\u00b1\7o\2\2\u00b1\u00b2\7n\2\2\u00b2"+
		"\62\3\2\2\2\u00b3\u00b5\t\t\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2"+
		"\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\64\3\2\2\2\u00b8\u00ba"+
		"\t\n\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\b\33\2\2\u00be\66\3\2\2"+
		"\2\u00bf\u00c0\13\2\2\2\u00c08\3\2\2\2\17\2MRXZc{\u0081\u0087\u0094\u009c"+
		"\u00b6\u00bb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}