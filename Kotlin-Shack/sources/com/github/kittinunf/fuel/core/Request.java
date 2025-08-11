package com.github.kittinunf.fuel.core;

import android.support.v4.app.NotificationCompat;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.deserializers.ByteArrayDeserializer;
import com.github.kittinunf.fuel.core.deserializers.StringDeserializer;
import com.github.kittinunf.fuel.core.requests.DownloadTaskRequest;
import com.github.kittinunf.fuel.core.requests.TaskRequest;
import com.github.kittinunf.fuel.core.requests.UploadTaskRequest;
import com.github.kittinunf.fuel.util.Base64;
import com.github.kittinunf.result.Result;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Request.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 ¬\u00012\u00020\u0001:\u0004¬\u0001\u00ad\u0001B\u0099\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b\u0012\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e0\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010\u0017J\u0016\u0010v\u001a\u00020\u00002\u0006\u0010w\u001a\u00020\u00052\u0006\u0010x\u001a\u00020\u0005J \u0010y\u001a\u00020\u00002\u0018\u0010y\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020z0VJ&\u0010{\u001a\u00020\u00002\u001e\u0010{\u001a\u001a\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020z0|0VJ\u000e\u0010}\u001a\u00020\u00002\u0006\u0010}\u001a\u00020~J\u0019\u0010}\u001a\u00020\u00002\u0006\u0010}\u001a\u00020\u00052\t\b\u0002\u0010\u007f\u001a\u00030\u0080\u0001J\u0007\u0010\u0081\u0001\u001a\u00020\u0005J\u0019\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u000f\u0010\u0084\u0001\u001a\n\u0012\u0005\u0012\u00030\u0083\u00010\u0085\u0001J\b\u0010\u0086\u0001\u001a\u00030\u0083\u0001J)\u0010\u0087\u0001\u001a\u00020\u00002 \u0010\u0087\u0001\u001a\u001b\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0088\u00010|0VJ#\u0010\u0089\u0001\u001a\u00020\u00002\u001a\u0010\u0089\u0001\u001a\u0015\u0012\u0004\u0012\u00020W\u0012\u0004\u0012\u00020\u0007\u0012\u0005\u0012\u00030\u008a\u00010VJ\t\u0010\u008b\u0001\u001a\u00020~H\u0002J?\u0010\u008c\u0001\u001a\u00020\u000020\u0010\u008d\u0001\u001a\u0019\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u008e\u0001\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0003\u0010\u008f\u0001J\u001f\u0010\u008c\u0001\u001a\u00020\u00002\u0016\u0010\u008d\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0090\u0001J1\u0010\u008c\u0001\u001a\u00020\u00002\u0016\u0010\u008d\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0090\u00012\b\u0010\u0091\u0001\u001a\u00030\u0092\u0001H\u0000¢\u0006\u0003\b\u0093\u0001J\u001d\u0010\u0094\u0001\u001a\u00020\u00002\u0014\u0010\u0094\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0000\u0012\u0005\u0012\u00030\u0083\u00010PJ\u0015\u0010\u0010\u001a\u00020\u00002\r\u0010\u0010\u001a\t\u0012\u0004\u0012\u00020\u00050\u0085\u0001JE\u0010\u0095\u0001\u001a\u00020\u00002<\u0010\u0096\u0001\u001a7\u0012\u0015\u0012\u00130\u001b¢\u0006\u000e\b\u0097\u0001\u0012\t\b\u0010\u0012\u0005\b\b(\u0098\u0001\u0012\u0015\u0012\u00130\u001b¢\u0006\u000e\b\u0097\u0001\u0012\t\b\u0010\u0012\u0005\b\b(\u0099\u0001\u0012\u0005\u0012\u00030\u0083\u00010VJ(\u0010\u009a\u0001\u001a#\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020~\u0012\u0005\u0012\u00030\u009d\u00010\u009c\u00010\u009b\u0001J7\u0010\u009a\u0001\u001a\u00020\u00002.\u0010\u0096\u0001\u001a)\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020~\u0012\u0005\u0012\u00030\u009d\u00010\u009c\u0001\u0012\u0005\u0012\u00030\u0083\u00010\u0019J\u0017\u0010\u009a\u0001\u001a\u00020\u00002\u000e\u0010\u0096\u0001\u001a\t\u0012\u0004\u0012\u00020~0\u009e\u0001JE\u0010\u009f\u0001\u001a$\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0013\u0012\u0011\u0012\u0005\u0012\u0003H \u0001\u0012\u0005\u0012\u00030\u009d\u00010\u009c\u00010\u009b\u0001\"\t\b\u0000\u0010 \u0001*\u00020\u000f2\u000f\u0010¡\u0001\u001a\n\u0012\u0005\u0012\u0003H \u00010¢\u0001JT\u0010\u009f\u0001\u001a\u00020\u0000\"\t\b\u0000\u0010 \u0001*\u00020\u000f2\u000f\u0010¡\u0001\u001a\n\u0012\u0005\u0012\u0003H \u00010¢\u00012/\u0010\u0096\u0001\u001a*\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0013\u0012\u0011\u0012\u0005\u0012\u0003H \u0001\u0012\u0005\u0012\u00030\u009d\u00010\u009c\u0001\u0012\u0005\u0012\u00030\u0083\u00010\u0019J4\u0010\u009f\u0001\u001a\u00020\u0000\"\t\b\u0000\u0010 \u0001*\u00020\u000f2\u000f\u0010¡\u0001\u001a\n\u0012\u0005\u0012\u0003H \u00010¢\u00012\u000f\u0010\u0096\u0001\u001a\n\u0012\u0005\u0012\u0003H \u00010\u009e\u0001J\u0017\u0010£\u0001\u001a\u00020\u00002\u000e\u0010\u0096\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u009e\u0001J5\u0010£\u0001\u001a#\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u009d\u00010\u009c\u00010\u009b\u00012\t\b\u0002\u0010\u007f\u001a\u00030\u0080\u0001H\u0007JB\u0010£\u0001\u001a\u00020\u00002\t\b\u0002\u0010\u007f\u001a\u00030\u0080\u00012.\u0010\u0096\u0001\u001a)\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u009d\u00010\u009c\u0001\u0012\u0005\u0012\u00030\u0083\u00010\u0019J \u0010£\u0001\u001a\u00020\u00002\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u000e\u0010\u0096\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u009e\u0001J#\u0010¤\u0001\u001a\u00020\u00002\u001a\u0010¤\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0012\u0005\u0012\u00030\u008a\u00010VJ)\u0010¥\u0001\u001a\u00020\u00002 \u0010¥\u0001\u001a\u001b\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008a\u00010|0VJ\u0016\u0010¦\u0001\u001a\u00030\u0083\u00012\f\u0010§\u0001\u001a\u0007\u0012\u0002\b\u00030¨\u0001J\u0010\u0010©\u0001\u001a\u00020\u00002\u0007\u0010©\u0001\u001a\u00020\u0015J\u0010\u0010ª\u0001\u001a\u00020\u00002\u0007\u0010©\u0001\u001a\u00020\u0015J\t\u0010«\u0001\u001a\u00020\u0005H\u0016R6\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001c\u00104\u001a\u0004\u0018\u000105X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R&\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b;\u0010<\u001a\u0004\b=\u00103R\u001a\u0010>\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b?\u0010<\u001a\u0004\b@\u0010AR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bD\u0010AR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\bI\u0010CR%\u0010\f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010CR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u0010FR\u0014\u0010L\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR(\u0010O\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0000\u0018\u00010PX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR.\u0010U\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020W\u0012\u0004\u0012\u00020W\u0018\u00010VX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001c\u0010\\\u001a\u0004\u0018\u00010]X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u0014\u0010b\u001a\b\u0012\u0002\b\u0003\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010d\u001a\u00020e8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bh\u0010i\u001a\u0004\bf\u0010gR\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u001a\u0010\u0016\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010k\"\u0004\bo\u0010mR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bt\u0010u¨\u0006®\u0001"}, d2 = {"Lcom/github/kittinunf/fuel/core/Request;", "Lcom/github/kittinunf/fuel/Fuel$RequestConvertible;", "method", "Lcom/github/kittinunf/fuel/core/Method;", "path", "", "url", "Ljava/net/URL;", "type", "Lcom/github/kittinunf/fuel/core/Request$Type;", "headers", "", "parameters", "", "Lkotlin/Pair;", "", "name", "names", "", "mediaTypes", "timeoutInMillisecond", "", "timeoutReadInMillisecond", "(Lcom/github/kittinunf/fuel/core/Method;Ljava/lang/String;Ljava/net/URL;Lcom/github/kittinunf/fuel/core/Request$Type;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;II)V", "bodyCallback", "Lkotlin/Function3;", "Ljava/io/OutputStream;", "", "getBodyCallback", "()Lkotlin/jvm/functions/Function3;", "setBodyCallback", "(Lkotlin/jvm/functions/Function3;)V", "callbackExecutor", "Ljava/util/concurrent/Executor;", "getCallbackExecutor$fuel", "()Ljava/util/concurrent/Executor;", "setCallbackExecutor$fuel", "(Ljava/util/concurrent/Executor;)V", "client", "Lcom/github/kittinunf/fuel/core/Client;", "getClient$fuel", "()Lcom/github/kittinunf/fuel/core/Client;", "setClient$fuel", "(Lcom/github/kittinunf/fuel/core/Client;)V", "executor", "Ljava/util/concurrent/ExecutorService;", "getExecutor$fuel", "()Ljava/util/concurrent/ExecutorService;", "setExecutor$fuel", "(Ljava/util/concurrent/ExecutorService;)V", "getHeaders", "()Ljava/util/Map;", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "getHostnameVerifier$fuel", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier$fuel", "(Ljavax/net/ssl/HostnameVerifier;)V", "httpHeaders", "httpHeaders$annotations", "()V", "getHttpHeaders", "httpMethod", "httpMethod$annotations", "getHttpMethod", "()Lcom/github/kittinunf/fuel/core/Method;", "getMediaTypes", "()Ljava/util/List;", "getMethod", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getNames", "getParameters", "getPath", "request", "getRequest", "()Lcom/github/kittinunf/fuel/core/Request;", "requestInterceptor", "Lkotlin/Function1;", "getRequestInterceptor$fuel", "()Lkotlin/jvm/functions/Function1;", "setRequestInterceptor$fuel", "(Lkotlin/jvm/functions/Function1;)V", "responseInterceptor", "Lkotlin/Function2;", "Lcom/github/kittinunf/fuel/core/Response;", "getResponseInterceptor$fuel", "()Lkotlin/jvm/functions/Function2;", "setResponseInterceptor$fuel", "(Lkotlin/jvm/functions/Function2;)V", "socketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getSocketFactory$fuel", "()Ljavax/net/ssl/SSLSocketFactory;", "setSocketFactory$fuel", "(Ljavax/net/ssl/SSLSocketFactory;)V", "taskFuture", "Ljava/util/concurrent/Future;", "taskRequest", "Lcom/github/kittinunf/fuel/core/requests/TaskRequest;", "getTaskRequest$fuel", "()Lcom/github/kittinunf/fuel/core/requests/TaskRequest;", "taskRequest$delegate", "Lkotlin/Lazy;", "getTimeoutInMillisecond", "()I", "setTimeoutInMillisecond", "(I)V", "getTimeoutReadInMillisecond", "setTimeoutReadInMillisecond", "getType", "()Lcom/github/kittinunf/fuel/core/Request$Type;", "setType", "(Lcom/github/kittinunf/fuel/core/Request$Type;)V", "getUrl", "()Ljava/net/URL;", "authenticate", "username", "password", "blob", "Lcom/github/kittinunf/fuel/core/Blob;", "blobs", "", "body", "", "charset", "Ljava/nio/charset/Charset;", "cUrlString", "callback", "", "f", "Lkotlin/Function0;", "cancel", "dataParts", "Lcom/github/kittinunf/fuel/core/DataPart;", "destination", "Ljava/io/File;", "getHttpBody", "header", "pairs", "", "([Lkotlin/Pair;)Lcom/github/kittinunf/fuel/core/Request;", "", "replace", "", "header$fuel", "interrupt", NotificationCompat.CATEGORY_PROGRESS, "handler", "Lkotlin/ParameterName;", "readBytes", "totalBytes", "response", "Lkotlin/Triple;", "Lcom/github/kittinunf/result/Result;", "Lcom/github/kittinunf/fuel/core/FuelError;", "Lcom/github/kittinunf/fuel/core/Handler;", "responseObject", "T", "deserializer", "Lcom/github/kittinunf/fuel/core/ResponseDeserializable;", "responseString", "source", "sources", "submit", "callable", "Ljava/util/concurrent/Callable;", "timeout", "timeoutRead", "toString", "Companion", "Type", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class Request implements Fuel.RequestConvertible {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Request.class), "taskRequest", "getTaskRequest$fuel()Lcom/github/kittinunf/fuel/core/requests/TaskRequest;"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    private Function3<? super Request, ? super OutputStream, ? super Long, Long> bodyCallback;

    @NotNull
    public Executor callbackExecutor;

    @NotNull
    public Client client;

    @NotNull
    public ExecutorService executor;

    @NotNull
    private final Map<String, String> headers;

    @Nullable
    private HostnameVerifier hostnameVerifier;

    @NotNull
    private final List<String> mediaTypes;

    @NotNull
    private final Method method;

    @NotNull
    private String name;

    @NotNull
    private final List<String> names;

    @NotNull
    private final List<Pair<String, Object>> parameters;

    @NotNull
    private final String path;

    @Nullable
    private Function1<? super Request, Request> requestInterceptor;

    @Nullable
    private Function2<? super Request, ? super Response, Response> responseInterceptor;

    @Nullable
    private SSLSocketFactory socketFactory;
    private Future<?> taskFuture;

    /* renamed from: taskRequest$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy taskRequest;
    private int timeoutInMillisecond;
    private int timeoutReadInMillisecond;

    @NotNull
    private Type type;

    @NotNull
    private final URL url;

    /* compiled from: Request.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/github/kittinunf/fuel/core/Request$Type;", "", "(Ljava/lang/String;I)V", "REQUEST", "DOWNLOAD", "UPLOAD", "fuel"}, k = 1, mv = {1, 1, 9})
    public enum Type {
        REQUEST,
        DOWNLOAD,
        UPLOAD
    }

    @Deprecated(message = "http naming is deprecated, use 'headers' instead", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    public static /* synthetic */ void httpHeaders$annotations() {
    }

    @Deprecated(message = "http naming is deprecated, use 'method' instead", replaceWith = @ReplaceWith(expression = "method", imports = {}))
    public static /* synthetic */ void httpMethod$annotations() {
    }

    @NotNull
    public final TaskRequest getTaskRequest$fuel() {
        Lazy lazy = this.taskRequest;
        KProperty kProperty = $$delegatedProperties[0];
        return (TaskRequest) lazy.getValue();
    }

    @JvmOverloads
    @NotNull
    public final Triple<Request, Response, Result<String, FuelError>> responseString() {
        return responseString$default(this, null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request(@NotNull Method method, @NotNull String path, @NotNull URL url, @NotNull Type type, @NotNull Map<String, String> headers, @NotNull List<? extends Pair<String, ? extends Object>> parameters, @NotNull String name, @NotNull List<String> names, @NotNull List<String> mediaTypes, int timeoutInMillisecond, int timeoutReadInMillisecond) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(names, "names");
        Intrinsics.checkParameterIsNotNull(mediaTypes, "mediaTypes");
        this.method = method;
        this.path = path;
        this.url = url;
        this.type = type;
        this.headers = headers;
        this.parameters = parameters;
        this.name = name;
        this.names = names;
        this.mediaTypes = mediaTypes;
        this.timeoutInMillisecond = timeoutInMillisecond;
        this.timeoutReadInMillisecond = timeoutReadInMillisecond;
        this.taskRequest = LazyKt.lazy(new Function0<TaskRequest>() { // from class: com.github.kittinunf.fuel.core.Request$taskRequest$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final TaskRequest invoke() {
                switch (Request.this.getType()) {
                    case DOWNLOAD:
                        return new DownloadTaskRequest(Request.this);
                    case UPLOAD:
                        return new UploadTaskRequest(Request.this);
                    default:
                        return new TaskRequest(Request.this);
                }
            }
        });
    }

    @NotNull
    public final Method getMethod() {
        return this.method;
    }

    @NotNull
    public final String getPath() {
        return this.path;
    }

    @NotNull
    public final URL getUrl() {
        return this.url;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Request(com.github.kittinunf.fuel.core.Method r15, java.lang.String r16, java.net.URL r17, com.github.kittinunf.fuel.core.Request.Type r18, java.util.Map r19, java.util.List r20, java.lang.String r21, java.util.List r22, java.util.List r23, int r24, int r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 8
            if (r1 == 0) goto La
            com.github.kittinunf.fuel.core.Request$Type r1 = com.github.kittinunf.fuel.core.Request.Type.REQUEST
            r6 = r1
            goto Lc
        La:
            r6 = r18
        Lc:
            r1 = r0 & 16
            if (r1 == 0) goto L19
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            java.util.Map r1 = (java.util.Map) r1
            r7 = r1
            goto L1b
        L19:
            r7 = r19
        L1b:
            r1 = r0 & 32
            if (r1 == 0) goto L25
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r8 = r1
            goto L27
        L25:
            r8 = r20
        L27:
            r1 = r0 & 64
            if (r1 == 0) goto L2f
            java.lang.String r1 = ""
            r9 = r1
            goto L31
        L2f:
            r9 = r21
        L31:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L3e
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            r10 = r1
            goto L40
        L3e:
            r10 = r22
        L40:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L4d
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            r11 = r1
            goto L4f
        L4d:
            r11 = r23
        L4f:
            r1 = r0 & 512(0x200, float:7.17E-43)
            if (r1 == 0) goto L57
            r1 = 15000(0x3a98, float:2.102E-41)
            r12 = r1
            goto L59
        L57:
            r12 = r24
        L59:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L5f
            r13 = r12
            goto L61
        L5f:
            r13 = r25
        L61:
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.kittinunf.fuel.core.Request.<init>(com.github.kittinunf.fuel.core.Method, java.lang.String, java.net.URL, com.github.kittinunf.fuel.core.Request$Type, java.util.Map, java.util.List, java.lang.String, java.util.List, java.util.List, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @NotNull
    public final Type getType() {
        return this.type;
    }

    public final void setType(@NotNull Type type) {
        Intrinsics.checkParameterIsNotNull(type, "<set-?>");
        this.type = type;
    }

    @NotNull
    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    @NotNull
    public final List<Pair<String, Object>> getParameters() {
        return this.parameters;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    @NotNull
    public final List<String> getNames() {
        return this.names;
    }

    @NotNull
    public final List<String> getMediaTypes() {
        return this.mediaTypes;
    }

    public final int getTimeoutInMillisecond() {
        return this.timeoutInMillisecond;
    }

    public final void setTimeoutInMillisecond(int i) {
        this.timeoutInMillisecond = i;
    }

    public final int getTimeoutReadInMillisecond() {
        return this.timeoutReadInMillisecond;
    }

    public final void setTimeoutReadInMillisecond(int i) {
        this.timeoutReadInMillisecond = i;
    }

    @NotNull
    /* renamed from: getHttpMethod, reason: from getter */
    public final Method getMethod() {
        return this.method;
    }

    @NotNull
    public final Map<String, String> getHttpHeaders() {
        return this.headers;
    }

    @Nullable
    public final Function3<Request, OutputStream, Long, Long> getBodyCallback() {
        return this.bodyCallback;
    }

    public final void setBodyCallback(@Nullable Function3<? super Request, ? super OutputStream, ? super Long, Long> function3) {
        this.bodyCallback = function3;
    }

    private final byte[] getHttpBody() {
        ByteArrayOutputStream $receiver = new ByteArrayOutputStream();
        Function3<? super Request, ? super OutputStream, ? super Long, Long> function3 = this.bodyCallback;
        if (function3 != null) {
            function3.invoke(getRequest(), $receiver, 0L);
        }
        byte[] byteArray = $receiver.toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "ByteArrayOutputStream().…s, 0)\n    }.toByteArray()");
        return byteArray;
    }

    @NotNull
    public final Client getClient$fuel() {
        Client client = this.client;
        if (client == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        return client;
    }

    public final void setClient$fuel(@NotNull Client client) {
        Intrinsics.checkParameterIsNotNull(client, "<set-?>");
        this.client = client;
    }

    @Nullable
    /* renamed from: getSocketFactory$fuel, reason: from getter */
    public final SSLSocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public final void setSocketFactory$fuel(@Nullable SSLSocketFactory sSLSocketFactory) {
        this.socketFactory = sSLSocketFactory;
    }

    @Nullable
    /* renamed from: getHostnameVerifier$fuel, reason: from getter */
    public final HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public final void setHostnameVerifier$fuel(@Nullable HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }

    @NotNull
    public final ExecutorService getExecutor$fuel() {
        ExecutorService executorService = this.executor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("executor");
        }
        return executorService;
    }

    public final void setExecutor$fuel(@NotNull ExecutorService executorService) {
        Intrinsics.checkParameterIsNotNull(executorService, "<set-?>");
        this.executor = executorService;
    }

    @NotNull
    public final Executor getCallbackExecutor$fuel() {
        Executor executor = this.callbackExecutor;
        if (executor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callbackExecutor");
        }
        return executor;
    }

    public final void setCallbackExecutor$fuel(@NotNull Executor executor) {
        Intrinsics.checkParameterIsNotNull(executor, "<set-?>");
        this.callbackExecutor = executor;
    }

    @Nullable
    public final Function1<Request, Request> getRequestInterceptor$fuel() {
        return this.requestInterceptor;
    }

    public final void setRequestInterceptor$fuel(@Nullable Function1<? super Request, Request> function1) {
        this.requestInterceptor = function1;
    }

    @Nullable
    public final Function2<Request, Response, Response> getResponseInterceptor$fuel() {
        return this.responseInterceptor;
    }

    public final void setResponseInterceptor$fuel(@Nullable Function2<? super Request, ? super Response, Response> function2) {
        this.responseInterceptor = function2;
    }

    @NotNull
    public final Request timeout(int timeout) {
        this.timeoutInMillisecond = timeout;
        return this;
    }

    @NotNull
    public final Request timeoutRead(int timeout) {
        this.timeoutReadInMillisecond = timeout;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final Request header(@NotNull Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        Pair<String, ? extends Object>[] $receiver$iv = pairs;
        for (Pair element$iv : $receiver$iv) {
            Pair it = element$iv;
            if (it != null) {
                Map<String, String> map = this.headers;
                Pair pair = new Pair(it.getFirst(), it.getSecond().toString());
                map.put(pair.getFirst(), pair.getSecond());
            }
        }
        return this;
    }

    @NotNull
    public final Request header(@Nullable Map<String, ? extends Object> pairs) {
        return header$fuel(pairs, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final Request header$fuel(@Nullable Map<String, ? extends Object> pairs, boolean replace) {
        if (pairs != null) {
            for (Map.Entry element$iv : pairs.entrySet()) {
                if (!this.headers.containsKey(element$iv.getKey()) || replace) {
                    Map<String, String> map = this.headers;
                    Pair pair = new Pair(element$iv.getKey(), element$iv.getValue().toString());
                    map.put(pair.getFirst(), pair.getSecond());
                }
            }
        }
        return this;
    }

    @NotNull
    public final Request body(@NotNull final byte[] body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        this.bodyCallback = new Function3<Request, OutputStream, Long, Long>() { // from class: com.github.kittinunf.fuel.core.Request$body$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Long invoke(Request request, OutputStream outputStream, Long l) {
                return Long.valueOf(invoke(request, outputStream, l.longValue()));
            }

            public final long invoke(@NotNull Request $noName_0, @Nullable OutputStream outputStream, long $noName_2) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                if (outputStream != null) {
                    outputStream.write(body);
                }
                return body.length;
            }
        };
        return this;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Request body$default(Request request, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return request.body(str, charset);
    }

    @NotNull
    public final Request body(@NotNull String body, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        byte[] bytes = body.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return body(bytes);
    }

    @NotNull
    public final Request authenticate(@NotNull String username, @NotNull String password) {
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        String auth = "" + username + ':' + password;
        Charset charset = Charsets.UTF_8;
        if (auth == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = auth.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] encodedAuth = Base64.encode(bytes, 2);
        StringBuilder sb = new StringBuilder();
        sb.append("Basic ");
        Intrinsics.checkExpressionValueIsNotNull(encodedAuth, "encodedAuth");
        sb.append(new String(encodedAuth, Charsets.UTF_8));
        return header(TuplesKt.to("Authorization", sb.toString()));
    }

    @NotNull
    public final Request progress(@NotNull Function2<? super Long, ? super Long, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        TaskRequest taskRequest = getTaskRequest$fuel();
        if (taskRequest instanceof DownloadTaskRequest) {
            ((DownloadTaskRequest) taskRequest).setProgressCallback(handler);
        } else if (taskRequest instanceof UploadTaskRequest) {
            ((UploadTaskRequest) taskRequest).setProgressCallback(handler);
        } else {
            throw new IllegalStateException("progress is only used with RequestType.DOWNLOAD or RequestType.UPLOAD");
        }
        return this;
    }

    @NotNull
    public final Request blobs(@NotNull Function2<? super Request, ? super URL, ? extends Iterable<Blob>> blobs) {
        Intrinsics.checkParameterIsNotNull(blobs, "blobs");
        TaskRequest taskRequest$fuel = getTaskRequest$fuel();
        if (!(taskRequest$fuel instanceof UploadTaskRequest)) {
            taskRequest$fuel = null;
        }
        UploadTaskRequest uploadTaskRequest = (UploadTaskRequest) taskRequest$fuel;
        if (uploadTaskRequest == null) {
            throw new IllegalStateException("source is only used with RequestType.UPLOAD");
        }
        uploadTaskRequest.setSourceCallback(blobs);
        return this;
    }

    @NotNull
    public final Request blob(@NotNull final Function2<? super Request, ? super URL, Blob> blob) {
        Intrinsics.checkParameterIsNotNull(blob, "blob");
        blobs(new Function2<Request, URL, List<? extends Blob>>() { // from class: com.github.kittinunf.fuel.core.Request$blob$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final List<Blob> invoke(@NotNull Request request, @NotNull URL $noName_1) {
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull($noName_1, "<anonymous parameter 1>");
                return CollectionsKt.listOf(Function2.this.invoke(request, request.getUrl()));
            }
        });
        return this;
    }

    @NotNull
    public final Request dataParts(@NotNull Function2<? super Request, ? super URL, ? extends Iterable<DataPart>> dataParts) {
        Intrinsics.checkParameterIsNotNull(dataParts, "dataParts");
        TaskRequest taskRequest$fuel = getTaskRequest$fuel();
        if (!(taskRequest$fuel instanceof UploadTaskRequest)) {
            taskRequest$fuel = null;
        }
        UploadTaskRequest uploadTaskRequest = (UploadTaskRequest) taskRequest$fuel;
        if (uploadTaskRequest == null) {
            throw new IllegalStateException("source is only used with RequestType.UPLOAD");
        }
        final Iterable parts = dataParts.invoke(getRequest(), getRequest().url);
        List $receiver = this.mediaTypes;
        $receiver.clear();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(parts, 10));
        for (Object item$iv$iv : parts) {
            DataPart it = (DataPart) item$iv$iv;
            destination$iv$iv.add(it.getType());
        }
        Iterable $receiver$iv = (List) destination$iv$iv;
        $receiver.addAll((Collection) $receiver$iv);
        List $receiver2 = this.names;
        $receiver2.clear();
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parts, 10));
        for (Object item$iv$iv2 : parts) {
            DataPart it2 = (DataPart) item$iv$iv2;
            destination$iv$iv2.add(it2.getName());
        }
        $receiver2.addAll((List) destination$iv$iv2);
        uploadTaskRequest.setSourceCallback(new Function2<Request, URL, List<? extends Blob>>() { // from class: com.github.kittinunf.fuel.core.Request$dataParts$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final List<Blob> invoke(@NotNull Request $noName_0, @NotNull URL $noName_1) {
                int $i$f$map = 0;
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull($noName_1, "<anonymous parameter 1>");
                Iterable $receiver$iv2 = parts;
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv2, 10));
                for (Object item$iv$iv3 : $receiver$iv2) {
                    DataPart $file = (DataPart) item$iv$iv3;
                    File file = $file.getFile();
                    String name = file.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
                    destination$iv$iv3.add(new Blob(name, file.length(), new Request$dataParts$3$1$1(file)));
                    $i$f$map = $i$f$map;
                }
                return (List) destination$iv$iv3;
            }
        });
        return this;
    }

    @NotNull
    public final Request sources(@NotNull Function2<? super Request, ? super URL, ? extends Iterable<? extends File>> sources) {
        Intrinsics.checkParameterIsNotNull(sources, "sources");
        this.mediaTypes.clear();
        this.names.clear();
        TaskRequest taskRequest$fuel = getTaskRequest$fuel();
        if (!(taskRequest$fuel instanceof UploadTaskRequest)) {
            taskRequest$fuel = null;
        }
        UploadTaskRequest uploadTaskRequest = (UploadTaskRequest) taskRequest$fuel;
        if (uploadTaskRequest == null) {
            throw new IllegalStateException("source is only used with RequestType.UPLOAD");
        }
        final Iterable files = sources.invoke(getRequest(), getRequest().url);
        uploadTaskRequest.setSourceCallback(new Function2<Request, URL, List<? extends Blob>>() { // from class: com.github.kittinunf.fuel.core.Request$sources$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final List<Blob> invoke(@NotNull Request $noName_0, @NotNull URL $noName_1) {
                int $i$f$map = 0;
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull($noName_1, "<anonymous parameter 1>");
                Iterable $receiver$iv = files;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv, 10));
                for (Object item$iv$iv : $receiver$iv) {
                    File it = (File) item$iv$iv;
                    String name = it.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                    destination$iv$iv.add(new Blob(name, it.length(), new Request$sources$1$1$1(it)));
                    $i$f$map = $i$f$map;
                }
                return (List) destination$iv$iv;
            }
        });
        return this;
    }

    @NotNull
    public final Request source(@NotNull final Function2<? super Request, ? super URL, ? extends File> source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        sources(new Function2<Request, URL, List<? extends File>>() { // from class: com.github.kittinunf.fuel.core.Request$source$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final List<File> invoke(@NotNull Request request, @NotNull URL $noName_1) {
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull($noName_1, "<anonymous parameter 1>");
                return CollectionsKt.listOf(Function2.this.invoke(request, request.getUrl()));
            }
        });
        return this;
    }

    @NotNull
    public final Request name(@NotNull Function0<String> name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name.invoke();
        return this;
    }

    @NotNull
    public final Request destination(@NotNull Function2<? super Response, ? super URL, ? extends File> destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        TaskRequest taskRequest$fuel = getTaskRequest$fuel();
        if (!(taskRequest$fuel instanceof DownloadTaskRequest)) {
            taskRequest$fuel = null;
        }
        DownloadTaskRequest downloadTaskRequest = (DownloadTaskRequest) taskRequest$fuel;
        if (downloadTaskRequest == null) {
            throw new IllegalStateException("destination is only used with RequestType.DOWNLOAD");
        }
        downloadTaskRequest.setDestinationCallback(destination);
        return this;
    }

    @NotNull
    public final Request interrupt(@NotNull Function1<? super Request, Unit> interrupt) {
        Intrinsics.checkParameterIsNotNull(interrupt, "interrupt");
        TaskRequest $receiver = getTaskRequest$fuel();
        $receiver.setInterruptCallback(interrupt);
        return this;
    }

    public final void submit(@NotNull Callable<?> callable) {
        Intrinsics.checkParameterIsNotNull(callable, "callable");
        ExecutorService executorService = this.executor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("executor");
        }
        this.taskFuture = executorService.submit(callable);
    }

    public final void callback(@NotNull final Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        Executor executor = this.callbackExecutor;
        if (executor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callbackExecutor");
        }
        executor.execute(new Runnable() { // from class: com.github.kittinunf.fuel.core.Request$callback$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }

    public final void cancel() {
        Future<?> future = this.taskFuture;
        if (future != null) {
            future.cancel(true);
        }
    }

    @Override // com.github.kittinunf.fuel.Fuel.RequestConvertible
    @NotNull
    public Request getRequest() {
        return this;
    }

    @NotNull
    public String toString() {
        StringBuilder $receiver = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("\"Body : ");
        sb.append((getHttpBody().length == 0) ^ true ? new String(getHttpBody(), Charsets.UTF_8) : "(empty)");
        sb.append(Typography.quote);
        $receiver.append(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        $receiver.append("\"Headers : (" + this.headers.size() + ")\"");
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            $receiver.append("" + key + " : " + value);
            Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
            StringsKt.appendln($receiver);
        }
        String sb2 = $receiver.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final String cUrlString() {
        StringBuilder $receiver = new StringBuilder();
        $receiver.append("$ curl -i");
        if (!Intrinsics.areEqual(this.method, Method.GET)) {
            $receiver.append(" -X " + this.method);
        }
        String escapedBody = StringsKt.replace$default(new String(getHttpBody(), Charsets.UTF_8), "\"", "\\\"", false, 4, (Object) null);
        if (escapedBody.length() > 0) {
            $receiver.append(" -d \"" + escapedBody + Typography.quote);
        }
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            $receiver.append(" -H \"" + key + ':' + value + Typography.quote);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        sb.append(this.url);
        $receiver.append(sb.toString());
        String sb2 = $receiver.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final Request response(@NotNull Function3<? super Request, ? super Response, ? super Result<byte[], FuelError>, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, INSTANCE.byteArrayDeserializer(), handler);
    }

    @NotNull
    public final Request response(@NotNull Handler<? super byte[]> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, INSTANCE.byteArrayDeserializer(), handler);
    }

    @NotNull
    public final Triple<Request, Response, Result<byte[], FuelError>> response() {
        return DeserializableKt.response(this, INSTANCE.byteArrayDeserializer());
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Request responseString$default(Request request, Charset charset, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return request.responseString(charset, (Function3<? super Request, ? super Response, ? super Result<String, FuelError>, Unit>) function3);
    }

    @NotNull
    public final Request responseString(@NotNull Charset charset, @NotNull Function3<? super Request, ? super Response, ? super Result<String, FuelError>, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, INSTANCE.stringDeserializer(charset), handler);
    }

    @NotNull
    public final Request responseString(@NotNull Charset charset, @NotNull Handler<? super String> handler) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, INSTANCE.stringDeserializer(charset), handler);
    }

    @NotNull
    public final Request responseString(@NotNull Handler<? super String> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, Companion.stringDeserializer$default(INSTANCE, null, 1, null), handler);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Triple responseString$default(Request request, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return request.responseString(charset);
    }

    @JvmOverloads
    @NotNull
    public final Triple<Request, Response, Result<String, FuelError>> responseString(@NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return DeserializableKt.response(this, INSTANCE.stringDeserializer(charset));
    }

    @NotNull
    public final <T> Request responseObject(@NotNull ResponseDeserializable<? extends T> deserializer, @NotNull Function3<? super Request, ? super Response, ? super Result<? extends T, FuelError>, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, deserializer, handler);
    }

    @NotNull
    public final <T> Request responseObject(@NotNull ResponseDeserializable<? extends T> deserializer, @NotNull Handler<? super T> handler) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(this, deserializer, handler);
    }

    @NotNull
    public final <T> Triple<Request, Response, Result<T, FuelError>> responseObject(@NotNull ResponseDeserializable<? extends T> deserializer) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return DeserializableKt.response(this, deserializer);
    }

    /* compiled from: Request.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/github/kittinunf/fuel/core/Request$Companion;", "", "()V", "byteArrayDeserializer", "Lcom/github/kittinunf/fuel/core/deserializers/ByteArrayDeserializer;", "stringDeserializer", "Lcom/github/kittinunf/fuel/core/deserializers/StringDeserializer;", "charset", "Ljava/nio/charset/Charset;", "fuel"}, k = 1, mv = {1, 1, 9})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final ByteArrayDeserializer byteArrayDeserializer() {
            return new ByteArrayDeserializer();
        }

        @NotNull
        public static /* bridge */ /* synthetic */ StringDeserializer stringDeserializer$default(Companion companion, Charset charset, int i, Object obj) {
            if ((i & 1) != 0) {
                charset = Charsets.UTF_8;
            }
            return companion.stringDeserializer(charset);
        }

        @NotNull
        public final StringDeserializer stringDeserializer(@NotNull Charset charset) {
            Intrinsics.checkParameterIsNotNull(charset, "charset");
            return new StringDeserializer(charset);
        }
    }
}
