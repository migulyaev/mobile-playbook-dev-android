package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
class NavDeepLink {
    private static final Pattern SCHEME_PATTERN = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");
    private final ArrayList<String> mArguments = new ArrayList<>();
    private final boolean mExactDeepLink;
    private final Pattern mPattern;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavDeepLink(String uri) {
        StringBuilder uriRegex = new StringBuilder("^");
        if (!SCHEME_PATTERN.matcher(uri).find()) {
            uriRegex.append("http[s]?://");
        }
        Pattern fillInPattern = Pattern.compile("\\{(.+?)\\}");
        Matcher matcher = fillInPattern.matcher(uri);
        int appendPos = 0;
        boolean exactDeepLink = !uri.contains(".*");
        while (matcher.find()) {
            String argName = matcher.group(1);
            this.mArguments.add(argName);
            uriRegex.append(Pattern.quote(uri.substring(appendPos, matcher.start())));
            uriRegex.append("(.+?)");
            appendPos = matcher.end();
            exactDeepLink = false;
        }
        if (appendPos < uri.length()) {
            uriRegex.append(Pattern.quote(uri.substring(appendPos)));
        }
        String finalRegex = uriRegex.toString().replace(".*", "\\E.*\\Q");
        this.mPattern = Pattern.compile(finalRegex);
        this.mExactDeepLink = exactDeepLink;
    }

    boolean matches(Uri deepLink) {
        return this.mPattern.matcher(deepLink.toString()).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isExactDeepLink() {
        return this.mExactDeepLink;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getMatchingArguments(Uri deepLink, Map<String, NavArgument> arguments) {
        Matcher matcher = this.mPattern.matcher(deepLink.toString());
        if (!matcher.matches()) {
            return null;
        }
        Bundle bundle = new Bundle();
        int size = this.mArguments.size();
        for (int index = 0; index < size; index++) {
            String argumentName = this.mArguments.get(index);
            String value = Uri.decode(matcher.group(index + 1));
            NavArgument argument = arguments.get(argumentName);
            if (argument != null) {
                NavType type = argument.getType();
                try {
                    type.parseAndPut(bundle, argumentName, value);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            } else {
                bundle.putString(argumentName, value);
            }
        }
        return bundle;
    }
}
