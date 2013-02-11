// $ANTLR 3.4 /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g 2013-02-11 16:02:42

package nl.cwi.swat.liveql.parser.antlr;
import nl.cwi.swat.liveql.ast.expr.*;
import nl.cwi.swat.liveql.ast.stat.*;
import nl.cwi.swat.liveql.ast.form.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class QLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "Ident", "Int", "Str", "WS", "'!'", "'!='", "'&&'", "'('", "')'", "'*'", "'+'", "'-'", "'/'", "':'", "'<'", "'<='", "'=='", "'>'", "'>='", "'bool'", "'else'", "'false'", "'form'", "'if'", "'int'", "'str'", "'true'", "'{'", "'||'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__10=10;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int COMMENT=4;
    public static final int Ident=5;
    public static final int Int=6;
    public static final int Str=7;
    public static final int WS=8;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public QLParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public QLParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[40+1];
         

    }

    public String[] getTokenNames() { return QLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g"; }


        @Override
        public void reportError(RecognitionException e) {
            throw new RuntimeException(e);
        }



    // $ANTLR start "form"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:25:1: form returns [Form result] : 'form' Ident body= block ;
    public final Form form() throws RecognitionException {
        Form result = null;

        int form_StartIndex = input.index();

        Token Ident1=null;
        Block body =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:26:3: ( 'form' Ident body= block )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:26:5: 'form' Ident body= block
            {
            match(input,27,FOLLOW_27_in_form56); if (state.failed) return result;

            Ident1=(Token)match(input,Ident,FOLLOW_Ident_in_form58); if (state.failed) return result;

            pushFollow(FOLLOW_block_in_form62);
            body=block();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result = new Form(new Ident((Ident1!=null?Ident1.getText():null), Ident1.getLine()), body); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, form_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "form"



    // $ANTLR start "block"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:29:1: block returns [Block result] : t= '{' (s= stat )* '}' ;
    public final Block block() throws RecognitionException {
        Block result = null;

        int block_StartIndex = input.index();

        Token t=null;
        Stat s =null;


         List<Stat> stats = new ArrayList<Stat>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:31:3: (t= '{' (s= stat )* '}' )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:31:5: t= '{' (s= stat )* '}'
            {
            t=(Token)match(input,32,FOLLOW_32_in_block90); if (state.failed) return result;

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:31:11: (s= stat )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==Str||LA1_0==28||LA1_0==32) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:31:13: s= stat
            	    {
            	    pushFollow(FOLLOW_stat_in_block96);
            	    s=stat();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { stats.add(s); }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,34,FOLLOW_34_in_block103); if (state.failed) return result;

            if ( state.backtracking==0 ) { result = new Block(stats, t.getLine()); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, block_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "block"



    // $ANTLR start "stat"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:34:1: stat returns [Stat result] : ( 'if' '(' c= orExpr ')' tru= stat 'else' fls= stat | 'if' '(' c= orExpr ')' tru= stat |b= block |l= label name= Ident ':' t= type '(' e= orExpr ')' |l= label name= Ident ':' t= type );
    public final Stat stat() throws RecognitionException {
        Stat result = null;

        int stat_StartIndex = input.index();

        Token name=null;
        Expr c =null;

        Stat tru =null;

        Stat fls =null;

        Block b =null;

        Label l =null;

        nl.cwi.swat.liveql.ast.types.Type t =null;

        Expr e =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:35:3: ( 'if' '(' c= orExpr ')' tru= stat 'else' fls= stat | 'if' '(' c= orExpr ')' tru= stat |b= block |l= label name= Ident ':' t= type '(' e= orExpr ')' |l= label name= Ident ':' t= type )
            int alt2=5;
            switch ( input.LA(1) ) {
            case 28:
                {
                int LA2_1 = input.LA(2);

                if ( (synpred2_QL()) ) {
                    alt2=1;
                }
                else if ( (synpred3_QL()) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;

                }
                }
                break;
            case 32:
                {
                alt2=3;
                }
                break;
            case Str:
                {
                int LA2_3 = input.LA(2);

                if ( (synpred5_QL()) ) {
                    alt2=4;
                }
                else if ( (true) ) {
                    alt2=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:35:5: 'if' '(' c= orExpr ')' tru= stat 'else' fls= stat
                    {
                    match(input,28,FOLLOW_28_in_stat124); if (state.failed) return result;

                    match(input,12,FOLLOW_12_in_stat126); if (state.failed) return result;

                    pushFollow(FOLLOW_orExpr_in_stat130);
                    c=orExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    match(input,13,FOLLOW_13_in_stat132); if (state.failed) return result;

                    pushFollow(FOLLOW_stat_in_stat136);
                    tru=stat();

                    state._fsp--;
                    if (state.failed) return result;

                    match(input,25,FOLLOW_25_in_stat138); if (state.failed) return result;

                    pushFollow(FOLLOW_stat_in_stat142);
                    fls=stat();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new IfThenElse(c, tru, fls); }

                    }
                    break;
                case 2 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:36:5: 'if' '(' c= orExpr ')' tru= stat
                    {
                    match(input,28,FOLLOW_28_in_stat150); if (state.failed) return result;

                    match(input,12,FOLLOW_12_in_stat152); if (state.failed) return result;

                    pushFollow(FOLLOW_orExpr_in_stat156);
                    c=orExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    match(input,13,FOLLOW_13_in_stat158); if (state.failed) return result;

                    pushFollow(FOLLOW_stat_in_stat162);
                    tru=stat();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new IfThen(c, tru); }

                    }
                    break;
                case 3 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:37:5: b= block
                    {
                    pushFollow(FOLLOW_block_in_stat172);
                    b=block();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = b; }

                    }
                    break;
                case 4 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:38:5: l= label name= Ident ':' t= type '(' e= orExpr ')'
                    {
                    pushFollow(FOLLOW_label_in_stat182);
                    l=label();

                    state._fsp--;
                    if (state.failed) return result;

                    name=(Token)match(input,Ident,FOLLOW_Ident_in_stat186); if (state.failed) return result;

                    match(input,18,FOLLOW_18_in_stat188); if (state.failed) return result;

                    pushFollow(FOLLOW_type_in_stat192);
                    t=type();

                    state._fsp--;
                    if (state.failed) return result;

                    match(input,12,FOLLOW_12_in_stat194); if (state.failed) return result;

                    pushFollow(FOLLOW_orExpr_in_stat198);
                    e=orExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    match(input,13,FOLLOW_13_in_stat200); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Computed(l, new Ident((name!=null?name.getText():null), name.getLine()), t, e); }

                    }
                    break;
                case 5 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:39:5: l= label name= Ident ':' t= type
                    {
                    pushFollow(FOLLOW_label_in_stat210);
                    l=label();

                    state._fsp--;
                    if (state.failed) return result;

                    name=(Token)match(input,Ident,FOLLOW_Ident_in_stat214); if (state.failed) return result;

                    match(input,18,FOLLOW_18_in_stat216); if (state.failed) return result;

                    pushFollow(FOLLOW_type_in_stat220);
                    t=type();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Answerable(l, new Ident((name!=null?name.getText():null), name.getLine()), t); }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, stat_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "stat"



    // $ANTLR start "label"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:42:1: label returns [Label result] : Str ;
    public final Label label() throws RecognitionException {
        Label result = null;

        int label_StartIndex = input.index();

        Token Str2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:43:3: ( Str )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:43:5: Str
            {
            Str2=(Token)match(input,Str,FOLLOW_Str_in_label242); if (state.failed) return result;

            if ( state.backtracking==0 ) { result = new Label((Str2!=null?Str2.getText():null), Str2.getLine()); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, label_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "label"



    // $ANTLR start "type"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:46:1: type returns [nl.cwi.swat.liveql.ast.types.Type result] : (t= 'int' |t= 'str' |t= 'bool' );
    public final nl.cwi.swat.liveql.ast.types.Type type() throws RecognitionException {
        nl.cwi.swat.liveql.ast.types.Type result = null;

        int type_StartIndex = input.index();

        Token t=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:47:3: (t= 'int' |t= 'str' |t= 'bool' )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt3=1;
                }
                break;
            case 30:
                {
                alt3=2;
                }
                break;
            case 24:
                {
                alt3=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:47:5: t= 'int'
                    {
                    t=(Token)match(input,29,FOLLOW_29_in_type265); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new nl.cwi.swat.liveql.ast.types.Int(t.getLine()); }

                    }
                    break;
                case 2 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:48:5: t= 'str'
                    {
                    t=(Token)match(input,30,FOLLOW_30_in_type275); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new nl.cwi.swat.liveql.ast.types.Str(t.getLine()); }

                    }
                    break;
                case 3 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:49:5: t= 'bool'
                    {
                    t=(Token)match(input,24,FOLLOW_24_in_type285); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new nl.cwi.swat.liveql.ast.types.Bool(t.getLine()); }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, type_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "type"



    // $ANTLR start "primary"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:52:1: primary returns [Expr result] : ( Int | Ident | Str | bool | '(' x= orExpr ')' );
    public final Expr primary() throws RecognitionException {
        Expr result = null;

        int primary_StartIndex = input.index();

        Token Int3=null;
        Token Ident4=null;
        Token Str5=null;
        Expr x =null;

        Expr bool6 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:53:3: ( Int | Ident | Str | bool | '(' x= orExpr ')' )
            int alt4=5;
            switch ( input.LA(1) ) {
            case Int:
                {
                alt4=1;
                }
                break;
            case Ident:
                {
                alt4=2;
                }
                break;
            case Str:
                {
                alt4=3;
                }
                break;
            case 26:
            case 31:
                {
                alt4=4;
                }
                break;
            case 12:
                {
                alt4=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:53:5: Int
                    {
                    Int3=(Token)match(input,Int,FOLLOW_Int_in_primary306); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Int(Integer.parseInt((Int3!=null?Int3.getText():null)), Int3.getLine()); }

                    }
                    break;
                case 2 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:54:5: Ident
                    {
                    Ident4=(Token)match(input,Ident,FOLLOW_Ident_in_primary316); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Ident((Ident4!=null?Ident4.getText():null), Ident4.getLine()); }

                    }
                    break;
                case 3 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:55:5: Str
                    {
                    Str5=(Token)match(input,Str,FOLLOW_Str_in_primary324); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Str((Str5!=null?Str5.getText():null), Str5.getLine()); }

                    }
                    break;
                case 4 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:56:5: bool
                    {
                    pushFollow(FOLLOW_bool_in_primary334);
                    bool6=bool();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = bool6; }

                    }
                    break;
                case 5 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:57:5: '(' x= orExpr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary343); if (state.failed) return result;

                    pushFollow(FOLLOW_orExpr_in_primary347);
                    x=orExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    match(input,13,FOLLOW_13_in_primary349); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = x; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, primary_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "primary"



    // $ANTLR start "bool"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:60:1: bool returns [Expr result] : (t= 'true' |t= 'false' );
    public final Expr bool() throws RecognitionException {
        Expr result = null;

        int bool_StartIndex = input.index();

        Token t=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:61:3: (t= 'true' |t= 'false' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==31) ) {
                alt5=1;
            }
            else if ( (LA5_0==26) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:61:5: t= 'true'
                    {
                    t=(Token)match(input,31,FOLLOW_31_in_bool371); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Bool(true, t.getLine()); }

                    }
                    break;
                case 2 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:62:5: t= 'false'
                    {
                    t=(Token)match(input,26,FOLLOW_26_in_bool382); if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Bool(false, t.getLine()); }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, bool_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "bool"



    // $ANTLR start "unExpr"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:65:1: unExpr returns [Expr result] : ( '+' x= unExpr | '-' x= unExpr | '!' x= unExpr |x= primary );
    public final Expr unExpr() throws RecognitionException {
        Expr result = null;

        int unExpr_StartIndex = input.index();

        Expr x =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:66:5: ( '+' x= unExpr | '-' x= unExpr | '!' x= unExpr |x= primary )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt6=1;
                }
                break;
            case 16:
                {
                alt6=2;
                }
                break;
            case 9:
                {
                alt6=3;
                }
                break;
            case Ident:
            case Int:
            case Str:
            case 12:
            case 26:
            case 31:
                {
                alt6=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:66:8: '+' x= unExpr
                    {
                    match(input,15,FOLLOW_15_in_unExpr408); if (state.failed) return result;

                    pushFollow(FOLLOW_unExpr_in_unExpr412);
                    x=unExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Pos(x); }

                    }
                    break;
                case 2 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:67:8: '-' x= unExpr
                    {
                    match(input,16,FOLLOW_16_in_unExpr423); if (state.failed) return result;

                    pushFollow(FOLLOW_unExpr_in_unExpr427);
                    x=unExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Neg(x); }

                    }
                    break;
                case 3 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:68:8: '!' x= unExpr
                    {
                    match(input,9,FOLLOW_9_in_unExpr438); if (state.failed) return result;

                    pushFollow(FOLLOW_unExpr_in_unExpr442);
                    x=unExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Not(x); }

                    }
                    break;
                case 4 :
                    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:69:8: x= primary
                    {
                    pushFollow(FOLLOW_primary_in_unExpr455);
                    x=primary();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = x; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, unExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "unExpr"



    // $ANTLR start "mulExpr"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:72:1: mulExpr returns [Expr result] : lhs= unExpr (op= ( '*' | '/' ) rhs= unExpr )* ;
    public final Expr mulExpr() throws RecognitionException {
        Expr result = null;

        int mulExpr_StartIndex = input.index();

        Token op=null;
        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:73:5: (lhs= unExpr (op= ( '*' | '/' ) rhs= unExpr )* )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:73:9: lhs= unExpr (op= ( '*' | '/' ) rhs= unExpr )*
            {
            pushFollow(FOLLOW_unExpr_in_mulExpr493);
            lhs=unExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:73:45: (op= ( '*' | '/' ) rhs= unExpr )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==14||LA7_0==17) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:73:47: op= ( '*' | '/' ) rhs= unExpr
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==14||input.LA(1)==17 ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_unExpr_in_mulExpr513);
            	    rhs=unExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { 
            	          if ((op!=null?op.getText():null).equals("*")) {
            	            result = new Mul(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("<=")) {
            	            result = new Div(result, rhs);      
            	          }
            	        }

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, mulExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "mulExpr"



    // $ANTLR start "addExpr"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:85:1: addExpr returns [Expr result] : lhs= mulExpr (op= ( '+' | '-' ) rhs= mulExpr )* ;
    public final Expr addExpr() throws RecognitionException {
        Expr result = null;

        int addExpr_StartIndex = input.index();

        Token op=null;
        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:86:5: (lhs= mulExpr (op= ( '+' | '-' ) rhs= mulExpr )* )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:86:9: lhs= mulExpr (op= ( '+' | '-' ) rhs= mulExpr )*
            {
            pushFollow(FOLLOW_mulExpr_in_addExpr554);
            lhs=mulExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:86:46: (op= ( '+' | '-' ) rhs= mulExpr )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= 15 && LA8_0 <= 16)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:86:48: op= ( '+' | '-' ) rhs= mulExpr
            	    {
            	    op=(Token)input.LT(1);

            	    if ( (input.LA(1) >= 15 && input.LA(1) <= 16) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_mulExpr_in_addExpr572);
            	    rhs=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { 
            	          if ((op!=null?op.getText():null).equals("+")) {
            	            result = new Add(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("-")) {
            	            result = new Sub(result, rhs);      
            	          }
            	        }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, addExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "addExpr"



    // $ANTLR start "relExpr"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:97:1: relExpr returns [Expr result] : lhs= addExpr (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )* ;
    public final Expr relExpr() throws RecognitionException {
        Expr result = null;

        int relExpr_StartIndex = input.index();

        Token op=null;
        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:98:5: (lhs= addExpr (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )* )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:98:9: lhs= addExpr (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )*
            {
            pushFollow(FOLLOW_addExpr_in_relExpr607);
            lhs=addExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:98:46: (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==10||(LA9_0 >= 19 && LA9_0 <= 23)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:98:48: op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==10||(input.LA(1) >= 19 && input.LA(1) <= 23) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_addExpr_in_relExpr631);
            	    rhs=addExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { 
            	          if ((op!=null?op.getText():null).equals("<")) {
            	            result = new LT(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("<=")) {
            	            result = new LEq(result, rhs);      
            	          }
            	          if ((op!=null?op.getText():null).equals(">")) {
            	            result = new GT(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals(">=")) {
            	            result = new GEq(result, rhs);      
            	          }
            	          if ((op!=null?op.getText():null).equals("==")) {
            	            result = new Eq(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("!=")) {
            	            result = new NEq(result, rhs);
            	          }
            	        }

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, relExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "relExpr"



    // $ANTLR start "andExpr"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:121:1: andExpr returns [Expr result] : lhs= relExpr ( '&&' rhs= relExpr )* ;
    public final Expr andExpr() throws RecognitionException {
        Expr result = null;

        int andExpr_StartIndex = input.index();

        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:122:5: (lhs= relExpr ( '&&' rhs= relExpr )* )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:122:9: lhs= relExpr ( '&&' rhs= relExpr )*
            {
            pushFollow(FOLLOW_relExpr_in_andExpr669);
            lhs=relExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:122:46: ( '&&' rhs= relExpr )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==11) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:122:48: '&&' rhs= relExpr
            	    {
            	    match(input,11,FOLLOW_11_in_andExpr675); if (state.failed) return result;

            	    pushFollow(FOLLOW_relExpr_in_andExpr679);
            	    rhs=relExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { result = new And(result, rhs); }

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, andExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "andExpr"



    // $ANTLR start "orExpr"
    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:126:1: orExpr returns [Expr result] : lhs= andExpr ( '||' rhs= andExpr )* ;
    public final Expr orExpr() throws RecognitionException {
        Expr result = null;

        int orExpr_StartIndex = input.index();

        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return result; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:127:5: (lhs= andExpr ( '||' rhs= andExpr )* )
            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:127:9: lhs= andExpr ( '||' rhs= andExpr )*
            {
            pushFollow(FOLLOW_andExpr_in_orExpr714);
            lhs=andExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result = lhs; }

            // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:127:48: ( '||' rhs= andExpr )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==33) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:127:50: '||' rhs= andExpr
            	    {
            	    match(input,33,FOLLOW_33_in_orExpr720); if (state.failed) return result;

            	    pushFollow(FOLLOW_andExpr_in_orExpr724);
            	    rhs=andExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { result = new Or(result, rhs); }

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, orExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "orExpr"

    // $ANTLR start synpred2_QL
    public final void synpred2_QL_fragment() throws RecognitionException {
        Expr c =null;

        Stat tru =null;

        Stat fls =null;


        // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:35:5: ( 'if' '(' c= orExpr ')' tru= stat 'else' fls= stat )
        // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:35:5: 'if' '(' c= orExpr ')' tru= stat 'else' fls= stat
        {
        match(input,28,FOLLOW_28_in_synpred2_QL124); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred2_QL126); if (state.failed) return ;

        pushFollow(FOLLOW_orExpr_in_synpred2_QL130);
        c=orExpr();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred2_QL132); if (state.failed) return ;

        pushFollow(FOLLOW_stat_in_synpred2_QL136);
        tru=stat();

        state._fsp--;
        if (state.failed) return ;

        match(input,25,FOLLOW_25_in_synpred2_QL138); if (state.failed) return ;

        pushFollow(FOLLOW_stat_in_synpred2_QL142);
        fls=stat();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_QL

    // $ANTLR start synpred3_QL
    public final void synpred3_QL_fragment() throws RecognitionException {
        Expr c =null;

        Stat tru =null;


        // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:36:5: ( 'if' '(' c= orExpr ')' tru= stat )
        // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:36:5: 'if' '(' c= orExpr ')' tru= stat
        {
        match(input,28,FOLLOW_28_in_synpred3_QL150); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred3_QL152); if (state.failed) return ;

        pushFollow(FOLLOW_orExpr_in_synpred3_QL156);
        c=orExpr();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred3_QL158); if (state.failed) return ;

        pushFollow(FOLLOW_stat_in_synpred3_QL162);
        tru=stat();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred3_QL

    // $ANTLR start synpred5_QL
    public final void synpred5_QL_fragment() throws RecognitionException {
        Token name=null;
        Label l =null;

        nl.cwi.swat.liveql.ast.types.Type t =null;

        Expr e =null;


        // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:38:5: (l= label name= Ident ':' t= type '(' e= orExpr ')' )
        // /Users/tvdstorm/CWI/LiveQL/src/nl/cwi/swat/liveql/parser/antlr/QL.g:38:5: l= label name= Ident ':' t= type '(' e= orExpr ')'
        {
        pushFollow(FOLLOW_label_in_synpred5_QL182);
        l=label();

        state._fsp--;
        if (state.failed) return ;

        name=(Token)match(input,Ident,FOLLOW_Ident_in_synpred5_QL186); if (state.failed) return ;

        match(input,18,FOLLOW_18_in_synpred5_QL188); if (state.failed) return ;

        pushFollow(FOLLOW_type_in_synpred5_QL192);
        t=type();

        state._fsp--;
        if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred5_QL194); if (state.failed) return ;

        pushFollow(FOLLOW_orExpr_in_synpred5_QL198);
        e=orExpr();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred5_QL200); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_QL

    // Delegated rules

    public final boolean synpred3_QL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_QL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_QL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_QL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_QL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_QL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_27_in_form56 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Ident_in_form58 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_block_in_form62 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_block90 = new BitSet(new long[]{0x0000000510000080L});
    public static final BitSet FOLLOW_stat_in_block96 = new BitSet(new long[]{0x0000000510000080L});
    public static final BitSet FOLLOW_34_in_block103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_stat124 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_stat126 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_stat130 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat132 = new BitSet(new long[]{0x0000000110000080L});
    public static final BitSet FOLLOW_stat_in_stat136 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_stat138 = new BitSet(new long[]{0x0000000110000080L});
    public static final BitSet FOLLOW_stat_in_stat142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_stat150 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_stat152 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_stat156 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat158 = new BitSet(new long[]{0x0000000110000080L});
    public static final BitSet FOLLOW_stat_in_stat162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_stat172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_stat182 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Ident_in_stat186 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_stat188 = new BitSet(new long[]{0x0000000061000000L});
    public static final BitSet FOLLOW_type_in_stat192 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_stat194 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_stat198 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_stat210 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Ident_in_stat214 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_stat216 = new BitSet(new long[]{0x0000000061000000L});
    public static final BitSet FOLLOW_type_in_stat220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Str_in_label242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_type265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_type285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Int_in_primary306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Ident_in_primary316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Str_in_primary324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bool_in_primary334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary343 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_primary347 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_bool371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_bool382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_unExpr408 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_unExpr_in_unExpr412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_unExpr423 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_unExpr_in_unExpr427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_unExpr438 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_unExpr_in_unExpr442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_unExpr455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unExpr_in_mulExpr493 = new BitSet(new long[]{0x0000000000024002L});
    public static final BitSet FOLLOW_set_in_mulExpr501 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_unExpr_in_mulExpr513 = new BitSet(new long[]{0x0000000000024002L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr554 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_set_in_addExpr562 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr572 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_addExpr_in_relExpr607 = new BitSet(new long[]{0x0000000000F80402L});
    public static final BitSet FOLLOW_set_in_relExpr615 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_addExpr_in_relExpr631 = new BitSet(new long[]{0x0000000000F80402L});
    public static final BitSet FOLLOW_relExpr_in_andExpr669 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_andExpr675 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_relExpr_in_andExpr679 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_andExpr_in_orExpr714 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_33_in_orExpr720 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_andExpr_in_orExpr724 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_28_in_synpred2_QL124 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred2_QL126 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_synpred2_QL130 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred2_QL132 = new BitSet(new long[]{0x0000000110000080L});
    public static final BitSet FOLLOW_stat_in_synpred2_QL136 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_synpred2_QL138 = new BitSet(new long[]{0x0000000110000080L});
    public static final BitSet FOLLOW_stat_in_synpred2_QL142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_synpred3_QL150 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred3_QL152 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_synpred3_QL156 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred3_QL158 = new BitSet(new long[]{0x0000000110000080L});
    public static final BitSet FOLLOW_stat_in_synpred3_QL162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred5_QL182 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Ident_in_synpred5_QL186 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_synpred5_QL188 = new BitSet(new long[]{0x0000000061000000L});
    public static final BitSet FOLLOW_type_in_synpred5_QL192 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred5_QL194 = new BitSet(new long[]{0x00000000840192E0L});
    public static final BitSet FOLLOW_orExpr_in_synpred5_QL198 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred5_QL200 = new BitSet(new long[]{0x0000000000000002L});

}