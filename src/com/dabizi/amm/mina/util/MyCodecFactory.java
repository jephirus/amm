package com.dabizi.amm.mina.util;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author lt 自定义编解码工厂
 */
public class MyCodecFactory implements ProtocolCodecFactory {
	
	private ProtocolEncoder encoder = null;

	private ProtocolDecoder decoder = null;

	public MyCodecFactory(ProtocolEncoder encoder, ProtocolDecoder decoder) {
		this.encoder = encoder;
		this.decoder = decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return this.encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return this.decoder;
	}
}
